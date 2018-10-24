
package com.sportradar.sdp.domain.common;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseLeague {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long leagueId;
    private String leagueName;
    private Integer priority;
    private Integer regionNum;
    private Long sportId;
    private Long leagueGroupId;

    private Date updatedTime;

    private String leagueIdXRefs;
}