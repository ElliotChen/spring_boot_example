package tw.elliot.storm.bolt;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import tw.elliot.storm.util.ApplicationContextHolder;
import tw.elliot.storm.util.SupportBean;

import java.util.Map;

@Slf4j
public class SecondBolt extends BaseRichBolt {
    OutputCollector outputCollector;
    SupportBean supportBean;
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;

        this.supportBean = ApplicationContextHolder.getBean(SupportBean.class);
    }

    @Override
    public void execute(Tuple tuple) {
        String message = tuple.getStringByField("message");
        log.debug("Got message:[{}]", message);
        this.supportBean.doSomething();

        this.outputCollector.emit("third1msg", new Values("fort1"));
        this.outputCollector.emit("third2msg", new Values("fort2"));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declareStream("third1msg",true,new Fields("message"));
        outputFieldsDeclarer.declareStream("third2msg",true,new Fields("message"));
    }
}
