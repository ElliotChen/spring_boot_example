package com.sportradar.sdp.domain.sdh;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "BetType_I18N")
@Data
@EqualsAndHashCode(of = {"betTypeId", "languageCode"})
public class BetTypeLanguage implements Serializable{

    @Id
    private Long betTypeId;
    @Id
    private Integer languageCode;

    private String betTypeName;

}
