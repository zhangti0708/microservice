package com.itcodai.springcloud.dao;

import com.itcodai.springcloud.entity.TOrder;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {

    @Select("select * from microservice where id = #{id}")
    TOrder findById(Long id);

    @Select("select * from microservice")
    List<TOrder> findAll();

}
