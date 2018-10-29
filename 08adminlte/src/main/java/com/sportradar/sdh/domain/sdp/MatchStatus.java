package com.sportradar.sdh.domain.sdp;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class MatchStatus {

	private Integer statusCode;

	private String statusName;

	private String statusDesc;

}
