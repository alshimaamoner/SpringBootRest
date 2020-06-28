package com.example.springMicroService.controller;

import com.example.springMicroService.model.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
public class HelloWorldController {
   //When You want to Try returning Hard Coded
    @GetMapping(path = "/HelloWorld")
    public String getHelloWorld(){
        return "Hello World";
    }
    //If You want to return Object
    @GetMapping(path = "/HelloWorldBean/{msg}")
    public HelloWorldBean getHelloWorldBean(@PathVariable String msg){
        return new HelloWorldBean(msg);
    }


}
