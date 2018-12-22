package tw.elliot.nettyclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class Client {
    public static final void main(String[] args) {
        final BlockingQueue<Optional> values = new LinkedBlockingQueue<Optional>();
        IO.Options opts = new IO.Options();
        opts.forceNew = true;
        try {
            Socket socket = IO.socket("http://localhost:9080", opts);
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    log.info("------Connected--------");
                }
            }).on("odds", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    log.info("Got Odds!");
                    for (Object obj : objects) {
                        log.info("[{}]",obj);
                    }
                }
            }).on("events", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    log.info("Got Events!");
                    for (Object obj : objects) {
                        log.info("[{}]",obj);
                    }


                }
            }).on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    log.info("on message");
                    for (Object obj : objects) {
                        log.info("[{}]",obj);
                    }
                }
            });


            socket.connect();


            OddsParams params = new OddsParams();
            params.setClientName("123");
            params.setLanguageCode("512");
            ObjectMapper objectMapper = new ObjectMapper();
            socket.emit("registerOdds", objectMapper.writeValueAsString(params));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
