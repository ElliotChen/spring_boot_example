package com.sportradar.sdp.domain.sdh;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "Competitor_I18N")
@Data
@EqualsAndHashCode(of = {"competitorId", "languageCode"})
public class CompetitorLanguage implements Serializable{

    @Id
    private Long competitorId;
    @Id
    private Integer languageCode;

    private String alias;

    private String competitorFullName;

    private String competitorShortName;

    @LastModifiedDate
    private Date updatedTime;

}
