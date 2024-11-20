package com.atguigu.schedule.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter(urlPatterns = {"/schedule/*"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //参数父转子
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        //获得session.域对象
        HttpSession session = request.getSession();


        //从session域中获得登录的用户对象
        Object attribute = session.getAttribute("sysUser");


        //判断用户对象是否为空
        if (null == attribute){
            //没登录跳login.html
            response.sendRedirect("/login.html");
        }else {
            //登录过  放行
            filterChain.doFilter(request, response);

        }

    }
}
