/**
 * Copyright (c) 2017
 * All rights reserved.
 */
package com.sportradar.sdp.domain.sdh;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "OddsDisplayFormat")
public class OddsDisplayFormat {
    @Id
    private Short displayFormatCode;

    private String displayFormatName;

    @OneToMany(fetch= FetchType.LAZY, mappedBy="displayFormatCode", cascade = {CascadeType.PERSIST})
    private List<OddsDisplayFormatLanguage> languages;
}
