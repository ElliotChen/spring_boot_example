package com.sportradar.sdp.domain.sdh;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.sportradar.sdp.domain.common.BaseSportMarket;
import lombok.Data;

@Data
@Entity
@Table(name = "SportMarket")
public class SportMarket extends BaseSportMarket {

    private String sportMarketXRefs;

    @Transient
    private List<com.sportradar.sdp.domain.dgt.SportMarket> dgtSportMarketXRefs;

    @OneToMany(fetch= FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumns({
            @JoinColumn(name="marketId", referencedColumnName="marketId"),
            @JoinColumn(name="sportId", referencedColumnName="sportId"),
            @JoinColumn(name="eventPartId", referencedColumnName="eventPartId")
    })
    private List<SportMarketLanguage> languages;
}