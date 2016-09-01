package com.lug.controller;

import com.lug.repository.UserPesitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzs on 2016/9/1.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/auth")
public class AuthController {

    private final UserPesitory userPesitory;

    @Autowired
    public AuthController(UserPesitory userPesitory){
        this.userPesitory = userPesitory;
    }

    @RequestMapping(value = "/home")
    public String userHome(Map<String,Object> map){

        //using map to set value by thymeleaf templates
        map.put("userName","233");
        return "/home";

    }

    /*
     * This is a simple test method for return json data
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map<String, Object> index(){

        Map<String, Object> map = new HashMap<String, Object>(3);

        map.put("state","OK");
        map.put("data","jklsdjfklajldfjalkjfklajflka");
        map.put("code","200");

        return map;
    }


}
