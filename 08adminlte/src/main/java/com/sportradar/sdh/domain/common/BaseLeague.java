
package com.sportradar.sdh.domain.common;

import java.util.Date;

import lombok.Data;

@Data
public abstract class BaseLeague extends BaseEntity {

    protected Long leagueId;
    protected String leagueName;
    protected Integer priority;
    protected Integer regionNum;
    protected Long sportId;
    protected Long leagueGroupId;

    protected Date updatedTime;

    protected String leagueIdXRefs;
}