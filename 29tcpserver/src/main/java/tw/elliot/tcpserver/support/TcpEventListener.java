package tw.elliot.tcpserver.support;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.integration.ip.tcp.connection.TcpConnectionCloseEvent;
import org.springframework.util.Assert;

@Slf4j
public class TcpEventListener implements ApplicationListener<TcpConnectionCloseEvent> {
	@Setter
	private SessionKeeper sessionKeeper;

	@Override
	public void onApplicationEvent(TcpConnectionCloseEvent tcpConnectionCloseEvent) {
		Assert.notNull(this.sessionKeeper, "SessionKeeper Can't be Null.");
		log.info("TCP Connection closed. [{}]", tcpConnectionCloseEvent.getConnectionId());
		this.sessionKeeper.disconnect(tcpConnectionCloseEvent.getConnectionId());
	}
}
