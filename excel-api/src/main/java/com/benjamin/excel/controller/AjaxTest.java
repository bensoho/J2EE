package com.benjamin.excel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxTest {

@RequestMapping("/test")
    public String testAjax(@RequestParam(name = "name") String name){
    System.out.println("param: " + name);
    return "Ajax test";
}



}
