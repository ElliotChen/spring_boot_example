/**
 * Copyright (c) 2017
 * All rights reserved.
 */
package com.sportradar.sdh.domain.sdp;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class TicketedOdds implements Serializable {

    private long oddsId;
    private long ticketId;
    private Short outcomeCode;
    private String ticketedOddsValue;
    private String ticketedMarketValue;
    private Byte ticketedMatchType;
    private Timestamp updatedTime;

}
