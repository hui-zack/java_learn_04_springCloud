package com.hui.service;

import com.hui.mapper.OrderMapper;
import com.hui.pojo.Order;

import com.hui.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;


    public Order queryOrderById(Long orderId) {

        Order order = orderMapper.findById(orderId);
        if (order == null){
            System.out.println(("[error] id don't find in database where id = " + orderId));
            return null;
        }

        /* 2-3-3 修改需要使用的服务url, 将ip+port替换为服务名称 */
        String url = "http://userservice:8081/user/" + order.getUserId();

        /* 1-1-1 在order模块的service中使用RestTemplate发送请求 */
        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);

        return order;
    }
}
