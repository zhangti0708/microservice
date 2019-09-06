package com.itcodai.springcloud.service;

import com.itcodai.springcloud.entity.TOrder;

import java.util.List;


public interface OrderService {

    TOrder findById(Long id);

    List<TOrder> findAll();
}
