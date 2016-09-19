package com.lug.controller;

import com.lug.model.Location;
import com.lug.service.UavService;
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
import java.util.List;
import java.util.function.DoubleToIntFunction;

/**
 * Created by zzs on 2016/9/2.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/uav")
public class UavController {

    private UavService uavService;

    @Autowired
    public UavController(UavService uavService) {
        this.uavService = uavService;
    }

    /*
    查看无人机的位置信息,需要做分页,需要实现按照时间来查询
     */
    @ResponseBody
    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public Result uavLocations(HttpServletRequest request, HttpSession session){
        Result jsonRender = new Result();
        jsonRender = jsonRender.okForList();

        //分页的基本参数，根据需要自己设置需要的参数把
        int page = Integer.parseInt(request.getParameter("page"));
        int rows = Integer.parseInt(request.getParameter("rows"));

        //这里用的是时间戳,需要做转化
        long startTimeStamp = Long.parseLong(request.getParameter("timeStart"));
        long endTimeStamp = Long.parseLong(request.getParameter("timeEnd"));
        String uuid = request.getParameter("uuid");

        Date startTime = new Date(startTimeStamp*1000L);
        Date endTime = new Date(endTimeStamp*1000L);

//        List<Location> locations = uavService.getLocations(uuid);

        return jsonRender;
    }


    /*
    无人机通过该接口添加无人的位置记录
     */
    @ResponseBody
    @RequestMapping(value = "/location/add", method = RequestMethod.POST)
    public void addUavLocation(HttpServletRequest request, HttpSession session){

        String uuid = request.getParameter("uuid");
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        double longitude = Double.parseDouble(request.getParameter("longitude"));
        double height = Double.parseDouble(request.getParameter("height"));

        uavService.addLocation(uuid,latitude,longitude,height);
    }

//    @ResponseBody
//    @RequestMapping(value = "/location/")
}
