package tw.elliot.nettyserver;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
//@EnableScheduling
public class NettyserverApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(NettyserverApplication.class, args);
	}
}
