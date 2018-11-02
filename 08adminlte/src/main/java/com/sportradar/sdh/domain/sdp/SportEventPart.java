package com.sportradar.sdh.domain.sdp;

import lombok.Data;

import java.io.Serializable;
@Data
public class SportEventPart implements Serializable {

    private Integer eventPartId;

    private Long sportId;

    private String eventPartName;

}