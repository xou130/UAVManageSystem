package com.lug.controller;

import com.lug.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by zzs on 2016/9/1.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/admin")
public class AdminController {

    private AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    @RequestMapping(value = "/home")
    public String adminHome(Map<String,Object> map){
        return "/admin/home";
    }



}
