/**
 * @Title: MatchInfo.java
 * @Package com.betmatrix.theonex.sport.model
 * @Description: TODO please write your description <BR> 
 * @author Louis
 * @date 2017年8月31日 下午1:50:24
 * @version V1.0
 * @Copyright: 2017 www.demo.com Inc. All rights reserved. 
 */

package com.sportradar.sdp.domain.sdh;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Table(name = "MatchInfo_I18N")
public class MatchInfoLanguage implements Serializable{

    @Id
    private Integer languageCode;
    @Id
    private Long matchId;

    @Id
    private Integer partnerId;
    /*
    @Id
    private Integer infoType;
    */
    @Id
    private Integer deviceTypeId;

    private String matchTitle;
    private String matchSubject;

    @LastModifiedDate
    private Date updatedTime;
}
