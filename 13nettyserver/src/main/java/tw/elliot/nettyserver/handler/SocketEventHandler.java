package tw.elliot.nettyserver.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SocketEventHandler {

    @Autowired
    private SocketIOServer socketIOServer;

    private Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    private Map<String, SocketIOClient> oddsClientMap = new ConcurrentHashMap<>();

    private Map<String, SocketIOClient> eventClientMap = new ConcurrentHashMap<>();

    private SocketIONamespace oddsClients = null;
    @OnConnect
    public void onConnect(SocketIOClient client) {
        //verify ip here
        String uuid = client.getSessionId().toString();
        log.info("uuid[{}] connect from ip[{}]", uuid, client.getRemoteAddress());
        this.clientMap.put(uuid, client);
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String uuid = client.getSessionId().toString();
        log.info("uuid[{}] disconnect from ip[{}]", uuid, client.getRemoteAddress());
        clientMap.remove(uuid);
        oddsClientMap.remove(uuid);
        eventClientMap.remove(uuid);
    }

    @OnEvent(value = "registerOdds")
    public void registerOdds(SocketIOClient client, String data, AckRequest request) {
        log.info("register data[{}]", data);
        String uuid = client.getSessionId().toString();
        if (clientMap.containsKey(uuid)) {
            this.oddsClientMap.put(uuid, client);
            log.info("Add Client[{}] as oddsClient.");
            request.sendAckData("add to odds client succeeded");
        }
    }

    @OnEvent(value = "registerEvents")
    public void registerEvents(SocketIOClient client, AckRequest request, Object data) {
        String uuid = client.getSessionId().toString();
        if (clientMap.containsKey(uuid)) {
            this.eventClientMap.put(uuid, client);
            log.info("Add Client[{}] as eventsClient.");
        }
    }

    public void sendOddsMessage(String odds) {
        log.debug("send odds:[{}]", odds);
        Set<Map.Entry<String, SocketIOClient>> entrySet = oddsClientMap.entrySet();
        entrySet.forEach(client -> {client.getValue().sendEvent("odds", odds);});
    }

    public void sendEventsMessage(String events) {
        log.debug("send events:[{}]", events);
        Set<Map.Entry<String, SocketIOClient>> entrySet = eventClientMap.entrySet();
        entrySet.forEach(client -> {client.getValue().sendEvent("events", events);});
    }
}
