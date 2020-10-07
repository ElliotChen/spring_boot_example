package tw.ellliot.swagger.ctrl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.ellliot.swagger.entity.Order;

/**
 * @author elliot
 */
@Api(tags = "訂單管理")
@Slf4j
@RestController
@RequestMapping("/ctrl/order")
public class OrderCtrl {
	/**
	 *
	 * @param order 未編號的訂單
	 * @return 編號的訂單
	 */
	@ApiOperation("建立訂單")
	@PostMapping("/create")
	public Order createOrder(Order order) {
		log.info("Invoke Create Order");
		order.setNumber("123456");
		return order;
	}
}
