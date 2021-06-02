package tw.elliot.tcpserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.IpHeaders;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNioServerConnectionFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;
import tw.elliot.tcpserver.handler.EntryHandler;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Configuration
public class TcpServerConfig {

	@Autowired
	public List<EntryHandler> entryHandlerList;

	@Bean
	@Qualifier("tcpConnectionFactory")
	public AbstractServerConnectionFactory serverConnectionFactory() {
		return new TcpNioServerConnectionFactory(5678);
	}

	@Bean
	@Qualifier("requestChannel")
	public MessageChannel requestChannel() {
		return new DirectChannel();
	}

	@Bean
	public TcpInboundGateway tcpInboundGateway(@Qualifier("tcpConnectionFactory") AbstractServerConnectionFactory abstractServerConnectionFactory,
											   @Qualifier("requestChannel") MessageChannel requestChannel) {
		TcpInboundGateway tcpInboundGateway = new TcpInboundGateway();
		tcpInboundGateway.setConnectionFactory(abstractServerConnectionFactory);
		tcpInboundGateway.setRequestChannel(requestChannel);
		return tcpInboundGateway;
	}

	@ServiceActivator(inputChannel = "requestChannel")
	public String handleRequest(String msg, @Header(IpHeaders.CONNECTION_ID) String connectionId) throws Exception {
		log.info("Accept Command [{}] from ID [{}]", msg, connectionId);

		AtomicReference<String> result = new AtomicReference<>("");
		entryHandlerList.stream().filter(handler -> {
			return handler.accept(msg);
		}).findFirst().ifPresent(handler -> {
			result.set(handler.handle(msg));
		});

		return result.get();
	}

}
