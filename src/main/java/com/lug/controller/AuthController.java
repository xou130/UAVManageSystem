package com.lug.controller;

import com.lug.model.Uav;
import com.lug.model.User;
import com.lug.service.AuthService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zzs on 2016/9/1.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
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

    //用户注册
    @ResponseBody
    @RequestMapping(value = "/registe", method = RequestMethod.POST)
    public Result registe(HttpServletRequest request, HttpSession session){
        Result jsonRender = new Result();

        String regUsername = request.getParameter("username");
        String regPassword = request.getParameter("password");
        String regEmail = request.getParameter("email");
        String regPhone = request.getParameter("phone");

        User user = new User(regUsername, regPassword, regPhone, null, regEmail, null, new Date());
        try{
            authService.registe(session,user);
        }catch (Exception e){
            e.printStackTrace();
            jsonRender.argError();
        }

        return jsonRender;
    }


    /*
    登录
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result authLogin(HttpServletRequest request, HttpSession session){
        Result jsonRender = new Result();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ( !authService.loginValidate(session, username, password) ){
            jsonRender.passError();
        }

        return jsonRender;
    }

    /*
    登出
     */
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result authLogout(HttpServletRequest request, HttpSession session){
        Result jsonRender = new Result();

        if (session.getAttribute("auth") == null){
            jsonRender.needAuth();
        }
        else{
            session.removeAttribute("auth");
            session.removeAttribute("authId");
        }

        return jsonRender;
    }

    /*
    修改个人信息，需要添加验证数据类型和长度等的代码
     */
    @ResponseBody
    @RequestMapping(value = "/update/detail", method = RequestMethod.POST)
    public Result authUpdate(HttpServletRequest request, HttpSession session){
        Result jsonRender = new Result();

        User user = (User) session.getAttribute("auth");
        user.setGroupName(request.getParameter("groupName"));
        user.setAddress(request.getParameter("address"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));

        authService.updateAuth(user);
        session.setAttribute("auth", user);
        return jsonRender;
    }

    /*
    用户修改密码
     */
    @ResponseBody
    @RequestMapping(value = "/update/password", method = RequestMethod.PUT)
    public Result authUpdatePwd(HttpServletRequest request, HttpSession session){
        Result jsonRender = new Result();

        User user = (User) session.getAttribute("auth");
        String oldPwd = request.getParameter("oldPassword");
        if (oldPwd.equals(user.getPassword())){
            String newPwd = request.getParameter("newPassword");
            authService.updatePwd(session, newPwd);
        }
        else{
            jsonRender.passError();
            jsonRender.put("Msg", "Old password error!");
        }

        return jsonRender;
    }

    /*
    添加无人机
     */
    @ResponseBody
    @RequestMapping(value = "/uav/add", method = RequestMethod.POST)
    public Result addUav(HttpServletRequest request, HttpSession session){
        Result jsonRender = new Result();

        String uuid = request.getParameter("uuid");
        Long userId = (Long) session.getAttribute("authId");
        String groupName = request.getParameter("groupName");
        String info = request.getParameter("info");

        Uav uav = new Uav(uuid,userId,groupName,info,new Date());
        authService.addUav(uav);
        return jsonRender;
    }

    /*
    返回用户的无人列表
     */
    @ResponseBody
    @RequestMapping(value = "/uavs", method = RequestMethod.GET)
    public Result getUavList(HttpServletRequest request, HttpSession session){
        Result jsonRender = new Result();

        Long userId = (Long) session.getAttribute("authId");
        List<Uav> uavs = authService.getUavs(userId);

        if (uavs != null)
            jsonRender.okForList();
        else
            jsonRender.put("Msg", "No Uav");

        return jsonRender;
    }

    /*
    删除无人机
     */
    @ResponseBody
    @RequestMapping(value = "/uav/delete", method = RequestMethod.DELETE)
    public Result delUav(HttpServletRequest request, HttpSession session){
        Result jsonRender = new Result();

        String uuid = request.getParameter("uuid");
        Long userId = (Long) session.getAttribute("authId");
        try{
            authService.delUav(userId, uuid);
        }catch (Exception e){
            e.printStackTrace();
            jsonRender.illegalMethod();
        }

        return jsonRender;
    }


}
