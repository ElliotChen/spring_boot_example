package com.sportradar.sdh.domain.dgt;

import com.sportradar.sdh.domain.common.BaseSportMarket;
import com.sportradar.sdh.domain.sdp.Market;
import com.sportradar.sdh.domain.sdp.Region;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
public class SportMarket extends BaseSportMarket {

    private Integer periodNum;

    private Market marketRef;

    public SportMarket(Long sportId, Long marketId, Integer eventPartId, Integer periodNum, Long refMarketId) {
        this.setSportId(sportId);
        this.setMarketId(marketId);
        this.setEventPartId(eventPartId);
        this.setPeriodNum(periodNum);

        this.marketRef = new Market(refMarketId);
    }


    public static SportMarket ofRefCompositeId(String compositeKey, Long refMarketId) {
        SportMarket sportMarket = null;

        if (StringUtils.isEmpty(compositeKey) || "-1 -1 -1 -1".equals(compositeKey)) {
            return sportMarket;
        }

        String[] keys = compositeKey.split(" ");

        sportMarket = new SportMarket();

        sportMarket.setSportId(Long.parseLong(keys[0]));
        sportMarket.setMarketId(Long.parseLong(keys[1]));
        sportMarket.setEventPartId(Integer.parseInt(keys[2]));
        sportMarket.setPeriodNum(Integer.parseInt(keys[3]));

        sportMarket.setMarketRef(new Market(refMarketId));

        return sportMarket;
    }

    @Override
    public String getCompositedId() {
        return String.valueOf(this.sportId + " " + this.marketId + " " + this.eventPartId + " " + this.periodNum);
    }
}