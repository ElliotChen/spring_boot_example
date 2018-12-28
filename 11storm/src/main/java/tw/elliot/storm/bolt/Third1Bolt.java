package tw.elliot.storm.bolt;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

@Slf4j
public class Third1Bolt extends BaseRichBolt {
	@Override
	public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {

	}

	@Override
	public void execute(Tuple tuple) {
		log.info("Third");
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
		outputFieldsDeclarer.declare(new Fields("final"));
	}
}
