/**
 * Copyright (c) 2017
 * All rights reserved.
 */
package com.sportradar.sdp.domain.sdh;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TicketedOdds")
public class TicketedOdds implements Serializable {

    @Id
    private long oddsId;
    @Id
    private long ticketId;
    private Short outcomeCode;
    private String ticketedOddsValue;
    private String ticketedMarketValue;
    private Byte ticketedMatchType;
    private Timestamp updatedTime;

}
