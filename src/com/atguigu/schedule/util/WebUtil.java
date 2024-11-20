package com.atguigu.schedule.util;

import com.atguigu.schedule.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class WebUtil {
    private static ObjectMapper objectMapper;
    static{
        objectMapper = new ObjectMapper();
    }


    //专门向客户端响应JSON串的方法
    public static void writeJson(HttpServletResponse resp, Result result ){
        resp.setContentType("application/json;charset=UTF-8");

        //告诉客户端响应的是一个JSON字符串
        try {
            String info = objectMapper.writeValueAsString(result);
            resp.getWriter().write(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
