package tw.elliot.tcpserver.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SessionKeeper {
	public Map<String, TcpSession> SESSIONS = new ConcurrentHashMap<>();

	public TcpSession create(String connectionId) {
		TcpSession tcpSession = this.get(connectionId);
		tcpSession.setStatus(SessionStatus.CONNECTED);

		return tcpSession;
	}

	public TcpSession authorize(String connectionId) {
		TcpSession tcpSession = this.get(connectionId);
		tcpSession.setStatus(SessionStatus.AUTHORIZED);

		return tcpSession;
	}

	public void disconnect(String connectionId) {
		log.debug("Try to remove session with key [{}]", connectionId);
		TcpSession remove = this.SESSIONS.remove(connectionId);
		if (null != remove) {
			log.debug("remove session with key [{}] success.", connectionId);

			if (log.isDebugEnabled()) {
				log.debug("[{}] Executed Entry:", connectionId);
				remove.getEntries().forEach(entry -> {
					log.debug("[{}] - [{}]", connectionId, entry);
				});
			}
		} else {
			log.warn("Remove not thing with key [{}]", connectionId);
		}
	}

	public void recordEntry(String connectionId, String entry) {
		this.get(connectionId).getEntries().add(entry);
	}

	public void put(String id, TcpSession session) {
		Assert.notNull(id, "Session id can't be null.");
		Assert.notNull(session, "Session can't be null.");

		SESSIONS.put(id,session);
	}

	public TcpSession get(String connectionId) {
		TcpSession tcpSession = SESSIONS.getOrDefault(connectionId, new TcpSession());
		SESSIONS.putIfAbsent(connectionId, tcpSession);
		return tcpSession;
	}


}
