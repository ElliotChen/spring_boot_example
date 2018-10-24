package com.sportradar.sdp.domain.sdh;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "MarketOption")
@Data
public class MarketOption implements Serializable {

    @Id
    private Long marketId;

    @Id
    private Integer optionNum;

    private String shortName;

    private String fullName;

    @LastModifiedDate
    private Date updatedTime;

}
