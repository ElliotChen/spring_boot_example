package tw.elliot.nettyserver.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tw.elliot.nettyserver.handler.SocketEventHandler;

@Slf4j
@Component
public class NettyKafkaListener {

    @Autowired
    private SocketEventHandler socketEventHandler;

    @KafkaListener(topics = {"betRadar_liveOdds"})
    public void oddsListener(String msg) {
        socketEventHandler.sendOddsMessage(msg);
    }

    @KafkaListener(topics = {"betRadar_liveScout"})
    public void eventsListener(String msg) {
        socketEventHandler.sendEventsMessage(msg);
    }
}
