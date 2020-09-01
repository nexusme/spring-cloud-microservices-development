package com.example.demo.appli;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient("eureka-order")
public interface OrderFeign {
    @RequestMapping("/orderTest")
    String orderTest();
}
