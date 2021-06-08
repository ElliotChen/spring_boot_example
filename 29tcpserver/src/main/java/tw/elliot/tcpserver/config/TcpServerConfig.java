package tw.elliot.tcpserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.event.inbound.ApplicationEventListeningMessageProducer;
import org.springframework.integration.ip.IpHeaders;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNioServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayRawSerializer;
import org.springframework.integration.ip.tcp.serializer.ByteArraySingleTerminatorSerializer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;
import tw.elliot.tcpserver.handler.EntryHandler;
import tw.elliot.tcpserver.support.ResScreenSerializer;
import tw.elliot.tcpserver.support.SessionKeeper;
import tw.elliot.tcpserver.support.TcpEventListener;
import tw.elliot.tcpserver.support.TcpSession;
import tw.elliot.tcpserver.util.ThreadLocalUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@EnableIntegration
@Configuration
public class TcpServerConfig {

	@Autowired
	public List<EntryHandler> entryHandlerList;

	@Bean
	@Scope("singleton")
	@Qualifier("tcpConnectionFactory")
	public AbstractServerConnectionFactory serverConnectionFactory() {
		TcpNioServerConnectionFactory serverConnectionFactory = new TcpNioServerConnectionFactory(5678);
		serverConnectionFactory.setSerializer(new ResScreenSerializer(true));
		serverConnectionFactory.setDeserializer(new ResScreenSerializer(false));
		return serverConnectionFactory;

	}

	@Bean
	@Qualifier("requestChannel")
	public MessageChannel requestChannel() {
		return new DirectChannel();
	}

	@Bean
	public TcpInboundGateway tcpInboundGateway(@Qualifier("tcpConnectionFactory") AbstractServerConnectionFactory serverConnectionFactory,
											   @Qualifier("requestChannel") MessageChannel requestChannel) {
		TcpInboundGateway tcpInboundGateway = new TcpInboundGateway();
		tcpInboundGateway.setConnectionFactory(serverConnectionFactory);
		tcpInboundGateway.setRequestChannel(requestChannel);
		return tcpInboundGateway;
	}

	@ServiceActivator(inputChannel = "requestChannel")
	public String handleRequest(String msg, @Header(IpHeaders.CONNECTION_ID) String connectionId) throws Exception {
		//Put tcp session into thread for handlers.
		SessionKeeper sessionKeeper = sessionKeeper();
		TcpSession tcpSession = sessionKeeper.get(connectionId);
		ThreadLocalUtil.TCP_SEESION.set(tcpSession);

		log.info("Accept Command [{}] from ID [{}]", msg, connectionId);
		AtomicReference<String> result = new AtomicReference<>("");
		entryHandlerList.stream().filter(handler -> {
			return handler.accept(msg);
		}).findFirst().ifPresent(handler -> {
			result.set(handler.handle(msg));
		});

		return result.get();
	}

	@Bean
	public TcpEventListener tcpEventListener(SessionKeeper sessionKeeper) {
		TcpEventListener listener = new TcpEventListener();
		listener.setSessionKeeper(sessionKeeper);
		return listener;
	}

	@Bean
	@Scope("singleton")
	public SessionKeeper sessionKeeper() {
		return new SessionKeeper();
	}

}
