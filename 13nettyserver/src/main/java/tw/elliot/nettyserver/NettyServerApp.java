package tw.elliot.nettyserver;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tw.elliot.nettyserver.cmd.ServerRunner;

public class NettyServerApp {
    public static final void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("tw.elliot.nettyserver");
        ServerRunner serverRunner = ctx.getBean(ServerRunner.class);
        try {
            serverRunner.run("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
