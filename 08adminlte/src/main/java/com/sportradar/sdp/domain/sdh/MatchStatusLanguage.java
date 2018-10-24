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

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "MatchStatus_I18N")
public class MatchStatusLanguage implements Serializable{

    @Id
    private Integer languageCode;
    @Id
    private Integer statusCode;

    private String statusName;
    private String statusDesc;

}
