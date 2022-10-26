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
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        String url = "http://localhost:8081/user/" + order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        // 4.返回
        order.setUser(user);

        String testUrl = "http://localhost:8085/api/alarm/news";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("cameraMac", "24:28:fd:de:cb:a4");
        HttpEntity<HashMap<String, Object>> hashMapHttpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(testUrl, hashMapHttpEntity, String.class);
        String body = responseEntity.getBody();
        System.out.println(body);

        return order;
    }
}
