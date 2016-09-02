package com.lug.controller;

import com.lug.model.User;
import com.lug.repository.AdminRepository;
import com.lug.repository.UserPesitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zzs on 2016/9/1.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/admin")
public class AdminController {

    private AdminRepository adminRepository;
    private UserPesitory userPesitory;

    @Autowired
    public AdminController(AdminRepository adminRepository, UserPesitory userPesitory){
        this.adminRepository = adminRepository;
        this.userPesitory = userPesitory;
    }

    /*
    管理员的主界面
     */
    @RequestMapping(value = "/home")
    public String adminHome(Map<String,Object> map){
        map.put("adminName","123");
        return "/admin/home";
    }

    /*d
    查询所有的普通用户
     */
    @ResponseBody
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public Map<String, Object> getUsers(){

        List<User> userList = userPesitory.findAll();

        Map<String, Object> modelMap = new LinkedHashMap<String, Object>();
        modelMap.put("totle",userList.size());
        modelMap.put("data",userList);
        modelMap.put("state","ok");
        modelMap.put("code",100);

        return modelMap;
    }

    /*
    使用用户的id进行查询用户的数据
     */
    @ResponseBody
    @RequestMapping(value = "/showUser/{id}", method = RequestMethod.GET)
    public Map<String, Object> showDetailOfUser(@PathVariable("id") Integer userId){

        User user = userPesitory.findOne(userId);

        Map<String, Object> modelMap = new LinkedHashMap<String, Object>();
        modelMap.put("data",user);
        modelMap.put("state","ok");
        modelMap.put("code",100);

        return modelMap;
    }

    /*
    根据用户的名字查看用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/showUserByName/{userName}", method = RequestMethod.GET)
    public Map<String, Object> showDetailOfUserByUserName(@PathVariable("userName") String userName){

        User user = userPesitory.findByUsername(userName);

        Map<String, Object> modelMap = new LinkedHashMap<String, Object>();
        if(user != null){
            modelMap.put("data",user);
            modelMap.put("state","ok");
            modelMap.put("code",100);
        }
        else{
            modelMap.put("state", "Can not fount "+userName);
            modelMap.put("code", 104);
        }

        return modelMap;
    }


    @RequestMapping(value = "/add")
    public Map<String, Object> addUser(){

        Map<String, Object> modelMap = new LinkedHashMap<String, Object>();

        return modelMap;

    }





}
