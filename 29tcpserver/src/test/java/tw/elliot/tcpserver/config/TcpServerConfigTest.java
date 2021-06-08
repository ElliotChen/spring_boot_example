package tw.elliot.tcpserver.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.ConsumerEndpointFactoryBean;
import org.springframework.integration.handler.BridgeHandler;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpConnectionSupport;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNioClientConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNioServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayRawSerializer;
import org.springframework.integration.ip.util.TestingUtilities;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

class TcpServerConfigTest {

	@Test
	public void testNetNotSingle() throws Exception {
		AbstractServerConnectionFactory scf = new TcpNetServerConnectionFactory(0);
		scf.setSingleUse(false);
		TcpInboundGateway gateway = new TcpInboundGateway();
		gateway.setConnectionFactory(scf);
		scf.start();
		TestingUtilities.waitListening(scf, 20000L);
		int port = scf.getPort();
		final QueueChannel channel = new QueueChannel();
		gateway.setRequestChannel(channel);
		gateway.setBeanFactory(mock(BeanFactory.class));
		ServiceActivatingHandler handler = new ServiceActivatingHandler(new Service());
		handler.setBeanFactory(mock(BeanFactory.class));
		handler.afterPropertiesSet();
		Socket socket = SocketFactory.getDefault().createSocket("localhost", port);
		socket.getOutputStream().write("Test1\r\n".getBytes());
		socket.getOutputStream().write("Test2\r\n".getBytes());
		handler.handleMessage(channel.receive(10000));
		handler.handleMessage(channel.receive(10000));
		byte[] bytes = new byte[12];
		readFully(socket.getInputStream(), bytes);
		assertThat(new String(bytes)).isEqualTo("Echo:Test1\r\n");
		readFully(socket.getInputStream(), bytes);
		assertThat(new String(bytes)).isEqualTo("Echo:Test2\r\n");
		gateway.stop();
		scf.stop();
	}

	private void readFully(InputStream is, byte[] buff) throws IOException {
		for (int i = 0; i < buff.length; i++) {
			buff[i] = (byte) is.read();
		}
	}

	private class Service {

		@SuppressWarnings("unused")
		public String serviceMethod(byte[] bytes) {
			return "Echo:" + new String(bytes);
		}

	}
}