package com.sportradar.sdp.domain.dgt;

import com.sportradar.sdp.domain.common.BaseSportMarket;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "SportMarket")
public class SportMarket extends BaseSportMarket {

    private String sportMarketXRefs;
    
}