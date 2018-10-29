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

import javax.persistence.*;
import java.util.Date;

@Data
public abstract class BaseMatchInfo {

    private Long matchId;
    private Boolean isInLive;
    private Long leagueId;
    private String matchTitle;
    private Date matchTime;
    private Long sportId;
    private Integer priority;
    private Boolean isActive;
    private String currentScore;
    private Integer status;
    private Date updatedTime;

    private String matchIdXRefs;

}
