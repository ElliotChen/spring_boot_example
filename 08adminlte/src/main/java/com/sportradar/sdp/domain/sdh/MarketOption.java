package com.sportradar.sdp.domain.sdh;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;

import com.sportradar.sdp.domain.common.BaseMarketOption;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "MarketOption")
@Data
public class MarketOption extends BaseMarketOption {

    @OneToMany(fetch= FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumns({
            @JoinColumn(name="marketId", referencedColumnName="marketId"),
            @JoinColumn(name="optionNum", referencedColumnName="optionNum")
    })
    private List<MarketOptionLanguage> languages;

    @Override
    public String getExpressId() {
        return String.valueOf(this.getMarketId()) + "-" + String.valueOf(this.getOptionNum());
    }

    @Override
    public String getIdXRefs() {
        return "";
    }

    @Override
    public void setMergedIdXRefs(String mergedIdXRefs) {

    }

    public String getLangString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        //Make sure Fetch executing
        List<MarketOptionLanguage> langs = this.getLanguages();

        if (null != langs && !langs.isEmpty()) {
            Iterator<MarketOptionLanguage> it = langs.iterator();

            while(it.hasNext()) {
                MarketOptionLanguage sl = it.next();
                sb.append(sl.getLanguageCode());
                sb.append(":");
                sb.append(sl.getFullName());
                if (it.hasNext()) {
                    sb.append(",");
                }
            }
        }

        sb.append("]");

        return sb.toString();
    }
}
