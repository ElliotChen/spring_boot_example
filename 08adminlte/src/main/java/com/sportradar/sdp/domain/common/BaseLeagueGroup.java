
package com.sportradar.sdp.domain.common;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class BaseLeagueGroup {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long leagueGroupId;
    private String leagueGroupName;

    private Long sportId;

    private String leagueGroupIdXRefs;
}