package tw.elliot.storm.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SupportBean {
    public void doSomething() {
        log.info("Yes, we invoke SupportBean Success.");
    }
}
