package com.itcodai.springcloud.controller;

import com.itcodai.springcloud.entity.TOrder;
import com.itcodai.springcloud.service.OrderService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/provider/order")
public class OrderProviderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProviderController.class);

    @Resource
    private EurekaClient client;
    @Resource
    private OrderService orderService;
    /**
     * 发现服务
     * 创建发现服务入口
     * */
    @GetMapping("/discovery")
    public Object discovery(){
        //获取Eureka中所有的服务节点
        List<Application> applications = client.getApplications().getRegisteredApplications();
        if(applications !=null){
            for(Application application:applications){
                //对外暴露的服务名称
                String name = application.getName();
                //只看订单服务信息
                if("MICROSERVICE-ORDER".equals(name)){
                    //服务有多少个实例，比如订单服务可能部署了多个，有多个订单服务注册到了Eureka
                    List<InstanceInfo> instances = application.getInstances();
                    if(instances != null){
                        for (InstanceInfo instance : instances){
                            LOGGER.info("服务id:{}",instance.getId());
                            LOGGER.info("服务主机:{}",instance.getHostName());
                            LOGGER.info("服务端口:{}",instance.getPort());
                        }
                    }
                    return instances;
                }
            }
        }
        return  null;
    }

    @GetMapping("/get/{id}")
    public TOrder getOrder(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/get/list")
    public List<TOrder> getAll() {
        return orderService.findAll();
    }
}
