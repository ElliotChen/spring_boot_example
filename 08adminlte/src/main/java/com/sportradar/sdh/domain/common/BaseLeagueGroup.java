
package com.sportradar.sdh.domain.common;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseLeagueGroup extends BaseEntity {

    protected Long leagueGroupId;
    protected String leagueGroupName;

    protected Long sportId;

    protected Date updatedTime;

    protected String leagueGroupIdXRefs;
}