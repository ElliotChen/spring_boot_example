package com.sportradar.sdp.domain.sdh;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SportEventPart")
@Data
public class SportEventPart implements Serializable {

    @Id
    private Integer eventPartId;

    @Id
    private Long sportId;

    private String eventPartName;

    @OneToMany(fetch= FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumns({
            @JoinColumn(name="sportId", referencedColumnName="sportId"),
            @JoinColumn(name="eventPartId", referencedColumnName="eventPartId")
    })
    private List<SportEventPartLanguage> languages;

}