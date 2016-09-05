package com.lug.controller;

import java.util.HashMap;

/**
 * Created by zzs on 2016/9/4.
 */
public class Result extends HashMap<String, Object> {
    public Result(){
        put("Code", 100);
        put("Msg", "OK");
    }

    public Result okForList(){
        put("Code", 200);
        return this;
    }
}

