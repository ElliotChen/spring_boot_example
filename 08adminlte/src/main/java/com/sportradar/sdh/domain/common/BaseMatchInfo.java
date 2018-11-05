/**
 * @Title: MatchInfo.java
 * @Package com.betmatrix.theonex.sport.model
 * @Description: TODO please write your description <BR> 
 * @author Louis
 * @date 2017年8月31日 下午1:50:24
 * @version V1.0
 * @Copyright: 2017 www.demo.com Inc. All rights reserved. 
 */

package com.sportradar.sdh.domain.common;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseMatchInfo extends BaseEntity {

    protected Long matchId;
    protected Boolean isInLive;
    protected Long leagueId;
    protected String matchTitle;
    protected Date matchTime;
    protected Long sportId;
    protected Integer priority;
    protected Boolean isActive;
    protected String currentScore;
    protected Integer status;
    protected Date updatedTime;

    protected String matchIdXRefs;

}
