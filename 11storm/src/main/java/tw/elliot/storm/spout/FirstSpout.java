package tw.elliot.storm.spout;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;

@Slf4j
public class FirstSpout extends BaseRichSpout {

    private int counter = 0;

    TopologyContext topologyContext = null;
    SpoutOutputCollector spoutOutputCollector = null;
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.topologyContext = topologyContext;
        this.spoutOutputCollector = spoutOutputCollector;
    }

    @Override
    public void nextTuple() {
        while (counter <= 2) {
            String message = "Okay, we do it "+counter+" time(s)";
            this.spoutOutputCollector.emit(new Values(message));
            counter++;
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("message"));
    }


    @Override
    public void ack(Object msgId) {
        super.ack(msgId);
        log.debug("Ack message[{}]", msgId);
    }
}
