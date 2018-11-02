
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

    protected Date createdTime;
    protected Date updatedTime;

    protected String leagueIdXRefs;

    @Override
    public String getCompositedId() {
        return String.valueOf(this.leagueId);
    }

    @Override
    public void setCompositedId(String compositedId) {
        this.leagueId = Long.parseLong(compositedId);
    }

}