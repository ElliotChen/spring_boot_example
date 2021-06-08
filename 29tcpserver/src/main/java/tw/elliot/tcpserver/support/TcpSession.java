package tw.elliot.tcpserver.support;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Data
public class TcpSession {
	private String id;
	private SessionStatus status;
	private List<String> entries = Collections.synchronizedList(new ArrayList<>());
	private Map<String, Object> dataMap = new ConcurrentHashMap<>();
}
