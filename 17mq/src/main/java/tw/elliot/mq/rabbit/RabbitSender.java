package tw.elliot.mq.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMessage(String message) {
		//this.rabbitTemplate.con
	}
}
