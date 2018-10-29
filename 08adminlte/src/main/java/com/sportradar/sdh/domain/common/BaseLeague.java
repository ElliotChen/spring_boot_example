
package com.sportradar.sdh.domain.common;

import java.util.Date;

import lombok.Data;

@Data
public abstract class BaseLeague extends BaseEntity {

    private Long leagueId;
    private String leagueName;
    private Integer priority;
    private Integer regionNum;
    private Long sportId;
    private Long leagueGroupId;

    private Date updatedTime;

    private String leagueIdXRefs;
}