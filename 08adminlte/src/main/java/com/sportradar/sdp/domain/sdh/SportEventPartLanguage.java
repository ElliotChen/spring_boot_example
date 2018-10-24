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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "SportEventPart_I18N")
public class SportEventPartLanguage implements Serializable{

    @Id
    private Integer languageCode;
    @Id
    private Long sportId;
    @Id
    private Integer eventPartId;

    private String periodSetName;

}
