package com.sportradar.sdp.domain.sdh;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "BetType")
public class BetType {
    @Id
    private Long betTypeId;
    private String betTypeName;

    @OneToMany(fetch= FetchType.LAZY, mappedBy="betTypeId", cascade = {CascadeType.PERSIST})
    private List<BetTypeLanguage> languages;
}
