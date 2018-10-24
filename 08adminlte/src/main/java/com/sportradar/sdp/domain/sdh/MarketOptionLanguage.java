package com.sportradar.sdp.domain.sdh;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "MarketOption_I18N")
public class MarketOptionLanguage implements Serializable{

    @Id
    private Long marketId;
    @Id
    private Integer languageCode;
    @Id
    private Integer optionNum;

    private String shortName;
    private String fullName;

}
