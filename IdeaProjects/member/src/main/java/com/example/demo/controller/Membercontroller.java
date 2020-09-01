package com.example.demo.controller;

import com.example.demo.appli.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Membercontroller {
    //    @RequestMapping("/memberTest")
//    public String orderTest(){
//        return "This is member server test.";
//    }

    @Autowired
    private OrderFeign orderFeign;
    @RequestMapping("/memTest")
    public String memTest(){

        String str = orderFeign.orderTest();
        return str;
    }

}