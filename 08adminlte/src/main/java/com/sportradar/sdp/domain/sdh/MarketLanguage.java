package com.sportradar.sdp.domain.sdh;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Market_I18N")
public class MarketLanguage implements Serializable{

    @Id
    private Long marketId;
    @Id
    private Integer languageCode;
    private String marketName;

}
