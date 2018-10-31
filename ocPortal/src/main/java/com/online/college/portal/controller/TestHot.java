package com.online.college.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * the class is create by @Author:oweson
 *
 * @Date£º2018/10/6 0006 11:36
 */
@Controller
public class TestHot {
    @RequestMapping("/hot")
    @ResponseBody
    public Object testHot(){
        return Arrays.asList(1,2,3,4,5,6,78,100);
    }
    @RequestMapping("/ppx")
    @ResponseBody
    public Object ppx(){

        return Arrays.asList(1,2,3,4,5,6);
    }
}
