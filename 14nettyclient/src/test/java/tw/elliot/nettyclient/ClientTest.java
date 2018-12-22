package tw.elliot.nettyclient;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientTest {
    private Socket socket;
    @Test
    public void testClient() {
        final BlockingQueue<Optional> values = new LinkedBlockingQueue<Optional>();
        IO.Options opts = new IO.Options();
        opts.forceNew = true;
        try {
            socket = IO.socket("http://localhost:9080/", opts);
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("--------------");
                    socket.emit("odds", "123");
                }
            }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println("--------------");
                    socket.emit("odds", "123");
                }
            });
            socket.connect();

            socket.disconnect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
