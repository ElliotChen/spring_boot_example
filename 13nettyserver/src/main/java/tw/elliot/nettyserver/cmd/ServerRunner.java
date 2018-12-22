package tw.elliot.nettyserver.cmd;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tw.elliot.nettyserver.handler.SocketEventHandler;

import javax.annotation.PreDestroy;

@Slf4j
@Component
public class ServerRunner implements CommandLineRunner {

    @Autowired
    private SocketIOServer server;

    @Autowired
    private SocketEventHandler handler;

    @Override
    public void run(String... args) throws Exception {
        log.info("Socket Server is ready");
        server.start();
    }

    @Scheduled(fixedDelay = 10000)
    public void scheduledMessage() {
        log.debug("scheduled");
        handler.sendOddsMessage("mock odds message: <xml></xml>");
    }

    @PreDestroy
    public void destroy() {
        log.info("Socket Server is closed");
        server.stop();
    }
}
