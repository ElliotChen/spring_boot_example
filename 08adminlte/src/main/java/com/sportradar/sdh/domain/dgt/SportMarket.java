package com.sportradar.sdh.domain.dgt;

import com.sportradar.sdh.domain.common.BaseSportMarket;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@NoArgsConstructor
public class SportMarket extends BaseSportMarket {

    private Integer periodNum;

    private com.sportradar.sdh.domain.sdp.Market marketRef;

    public SportMarket(Long sportId, Long marketId, Integer eventPartId, Integer periodNum, Long refMarketId) {
        this.setSportId(sportId);
        this.setMarketId(marketId);
        this.setEventPartId(eventPartId);
        this.setPeriodNum(periodNum);

        this.marketRef = new com.sportradar.sdh.domain.sdp.Market(refMarketId);
    }


    public static SportMarket ofRefCompositeId(String compositeKey, Long refMarketId) {
        SportMarket sportMarket = null;

        if (StringUtils.isEmpty(compositeKey)) { //  || "-1 -1 -1 -1".equals(compositeKey)) {
            return sportMarket;
        }

        String[] keys = compositeKey.split(" ");

        sportMarket = new SportMarket();

        sportMarket.setSportId(Long.parseLong(keys[0]));
        sportMarket.setMarketId(Long.parseLong(keys[1]));
        sportMarket.setEventPartId(Integer.parseInt(keys[2]));
        sportMarket.setPeriodNum(Integer.parseInt(keys[3]));

        sportMarket.setMarketRef(new com.sportradar.sdh.domain.sdp.Market(refMarketId));

        return sportMarket;
    }

    @Override
    public String getCompositedId() {
        return String.valueOf(this.sportId + " " + this.eventPartId + " " + this.periodNum + " " + this.marketId);
    }

    @Override
    public void setCompositedId(String compositedId) {
        String[] ids = compositedId.split(" ");
        this.sportId = Long.parseLong(ids[0]);
        this.eventPartId = Integer.parseInt(ids[1]);
        this.periodNum = Integer.parseInt(ids[2]);
        this.marketId = Long.parseLong(ids[3]);
    }
    @Override
    public SourceTypeEnum getSourceType() {
        return SourceTypeEnum.DGT;
    }
}