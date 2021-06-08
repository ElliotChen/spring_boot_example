package tw.elliot.tcpserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "res")
public class ResConfigData {
	private String entryResultPath;
	private String charEncoding;
	private String fileExtension;
}
