package tw.elliot.tcpserver.support;

import org.springframework.util.Assert;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class SessionKeeper {
	public static final Map<String, TcpSession> SESSIONS = new ConcurrentHashMap<>();

	public void put(String id, TcpSession session) {
		Assert.notNull(id, "Session id can't be null.");
		Assert.notNull(session, "Session can't be null.");

		SESSIONS.put(id,session);
	}

	public Optional<TcpSession> get(String id) {
		Assert.notNull(id, "Session id can't be null.");
		//return SESSIONS.containsKey(id) ? Optional.of(SESSIONS.get(id)) : Optional.empty();
		return Optional.ofNullable(SESSIONS.get(id));
	}


}
