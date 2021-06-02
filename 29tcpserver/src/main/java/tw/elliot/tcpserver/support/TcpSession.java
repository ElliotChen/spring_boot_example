package tw.elliot.tcpserver.support;

import lombok.Data;

import java.util.Map;

@Data
public class TcpSession {
	private String id;
	private Map<String, Object> dataMap;
}
