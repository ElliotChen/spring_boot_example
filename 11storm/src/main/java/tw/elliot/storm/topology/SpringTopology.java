package tw.elliot.storm.topology;

import lombok.extern.slf4j.Slf4j;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.springframework.stereotype.Component;
import tw.elliot.storm.bolt.SecondBolt;
import tw.elliot.storm.spout.FirstSpout;

@Slf4j
@Component
public class SpringTopology {

    TopologyBuilder topologyBuilder;
    Config config;
    public void submitRemote() {
        try {
            StormSubmitter.submitTopology("ST", config, topologyBuilder.createTopology());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void submitLocal() {
        initBuilderAndConfig();

        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("ST", config, topologyBuilder.createTopology());
    }

    private void initBuilderAndConfig() {
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("FirstSpout", new FirstSpout(), 1);

        topologyBuilder.setBolt("SecondBolt", new SecondBolt(), 1).setNumTasks(1).shuffleGrouping("FirstSpout");

        config = new Config();
        config.setNumAckers(1);
        config.setNumWorkers(1);
    }
}
