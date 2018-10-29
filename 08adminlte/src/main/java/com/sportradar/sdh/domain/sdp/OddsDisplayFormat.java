/**
 * Copyright (c) 2017
 * All rights reserved.
 */
package com.sportradar.sdh.domain.sdp;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Data
public class OddsDisplayFormat {
    private Short displayFormatCode;

    private String displayFormatName;

}
