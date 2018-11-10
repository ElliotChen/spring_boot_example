package tw.elliot.storm.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext APP_CONTEXT;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.debug("Got Application Context : [{}]", applicationContext);
        APP_CONTEXT = applicationContext;
    }

    public static Object getBean(String name) {
        return APP_CONTEXT.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return APP_CONTEXT.getBean(clazz);
    }

}
