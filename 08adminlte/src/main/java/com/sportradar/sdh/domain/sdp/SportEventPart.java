package com.sportradar.sdh.domain.sdp;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
public class SportEventPart implements Serializable {

    private Integer eventPartId;

    private Long sportId;

    private String eventPartName;

}