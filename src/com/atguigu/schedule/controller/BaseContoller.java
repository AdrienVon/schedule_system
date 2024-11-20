package com.atguigu.schedule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BaseContoller extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1];
        //使用反射通过方法名获得下面的方法
        Class aClass = this.getClass();
        //获取方法
        try {
            Method declaredMethod = aClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //暴力破解方法的访问修饰符限制
            declaredMethod.setAccessible(true);
            //执行方法

            declaredMethod.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

@Test
public void test(){
    Integer a = 100;
    Integer b = 100;

    if (a == b) {
        System.out.println("a");
    }


    Integer c = 300;
    Integer d = 300;

    if (c == d) {
        System.out.println("true");
    }
}
}
