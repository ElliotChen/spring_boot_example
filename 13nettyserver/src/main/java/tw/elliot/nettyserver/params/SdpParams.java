package tw.elliot.nettyserver.params;

import lombok.Data;

import java.util.Date;

@Data
public class SdpParams {
    private String clientName;
    private String languageCode;
    private Date startDate;
    private Date endDate;
    private Long sportId;
    private Long matchId;
}
