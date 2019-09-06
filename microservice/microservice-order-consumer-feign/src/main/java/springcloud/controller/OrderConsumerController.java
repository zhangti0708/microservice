package springcloud.controller;

import com.itcodai.springcloud.entity.TOrder;
import com.itcodai.springcloud.service.OrderClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单消费服务
 * @author shengwu ni
 */
@RestController
@RequestMapping("/consumer/order")
public class OrderConsumerController {

    /**
     * 上面定义的 Feign Client
     */
    @Resource
    private OrderClientService orderClientService;

    @GetMapping("/get/{id}")
    public TOrder getOrder(@PathVariable Long id) {
        return orderClientService.getOrder(id);
    }

    @GetMapping("/get/list")
    public List<TOrder> getAll() {
        return orderClientService.getAll();
    }
}

