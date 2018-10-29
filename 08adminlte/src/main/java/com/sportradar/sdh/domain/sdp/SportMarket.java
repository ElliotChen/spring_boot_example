package com.sportradar.sdh.domain.sdp;

import java.util.List;

import javax.persistence.*;

import com.sportradar.sdh.domain.common.BaseSportMarket;
import lombok.Data;

@Data
public class SportMarket extends BaseSportMarket {

    private String sportMarketXRefs;

    private List<com.sportradar.sdh.domain.dgt.SportMarket> dgtSportMarketXRefs;

}