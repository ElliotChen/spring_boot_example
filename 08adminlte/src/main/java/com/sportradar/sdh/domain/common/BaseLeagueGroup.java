
package com.sportradar.sdh.domain.common;

import lombok.Data;

import javax.persistence.*;

@Data
public abstract class BaseLeagueGroup {

    protected Long leagueGroupId;
    protected String leagueGroupName;

    protected Long sportId;

    protected String leagueGroupIdXRefs;
}