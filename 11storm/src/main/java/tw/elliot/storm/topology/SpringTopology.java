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

import java.util.Arrays;

@Slf4j
@Component
public class SpringTopology {

    TopologyBuilder topologyBuilder;
    Config config;
    public void submitRemote() {
        initBuilderAndConfig();

        config = new Config();
        /*
        config.put(Config.NIMBUS_HOST,"localhost");
        config.put(Config.NIMBUS_THRIFT_PORT,6627);
        config.put(Config.STORM_ZOOKEEPER_SERVERS, Arrays.asList("127.0.0.1"));
        config.put(Config.STORM_ZOOKEEPER_PORT,2181);
        */
        config.setDebug(Boolean.TRUE);


        config.setNumAckers(1);
        config.setNumWorkers(1);
        //System.setProperty("storm.jar","/Users/elliot/gitrepo/spring_boot_example/11storm/target/11storm-1.0-SNAPSHOT.jar");
        try {
            StormSubmitter.submitTopology("ST", config, topologyBuilder.createTopology());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void submitLocal() {
        initBuilderAndConfig();

        config = new Config();
        config.setNumAckers(1);
        config.setNumWorkers(1);
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("ST", config, topologyBuilder.createTopology());
    }

    private void initBuilderAndConfig() {
        topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("FirstSpout", new FirstSpout(), 1);

        topologyBuilder.setBolt("SecondBolt", new SecondBolt(), 1).setNumTasks(1).shuffleGrouping("FirstSpout");


    }
}
