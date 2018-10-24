package com.sportradar.sdp.domain.sdh;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
@Table(name = "League_I18N")
public class LeagueLanguage implements Serializable{

    @Id
    private Long leagueId;
    @Id
    private Integer languageCode;
    private String leagueName;

    private Date updatedTime;

}
