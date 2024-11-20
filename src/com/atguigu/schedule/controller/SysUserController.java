package com.atguigu.schedule.controller;

import com.atguigu.schedule.common.Result;
import com.atguigu.schedule.common.ResultCodeEnum;
import com.atguigu.schedule.pojo.SysUser;
import com.atguigu.schedule.service.SysUserService;
import com.atguigu.schedule.service.impl.SysUserServiceimpl;
import com.atguigu.schedule.util.MD5Util;
import com.atguigu.schedule.util.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/*")
public class SysUserController extends BaseContoller{
    private SysUserService userService = new SysUserServiceimpl();



    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //1接收客户端提交的参数
        String username = req.getParameter("username");
        String userPwd = req.getParameter("userPwd");
        //2调用服务成方法，根据用户名查询用户信息
        SysUser loginUser = userService.findByUsername(username);
        if(null == loginUser){
            resp.sendRedirect("/loginUsernameError.html");
        } else if (! MD5Util.encrypt(userPwd).equals(loginUser.getUserPwd())) {
            //3判断密码是否匹配
            resp.sendRedirect("/loginUsernameError.html");
        }else {
            //登录成功后将登陆的用户信息放入session
            HttpSession session = req.getSession();
            session.setAttribute("sysUser", loginUser);
            //4跳转首页
            resp.sendRedirect("/showSchedule.html");
        }

    }



    /**
     *注册时，接收用户要注册的用户名校验用户名是否被占用的业务接口(业务接口，不是java中的interface)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUsernameUsed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //接收用户名
        String username = req.getParameter("username");
        //调用服务处业务处理方法
        SysUser sysUser = userService.findByUsername(username);

        Result result = Result.ok(null);

        if (null != sysUser){
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp, result);


    }



    /**
     *接收用户注册请求的业务处理方法(业务接口，不是java中的interface)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        //1接收客户端提交的参数
        String username = req.getParameter("username");
        String userPwd = req.getParameter("userPwd");
        //2调用服务层方法，完成注册功能
            //将参数放入一个SysUser中，在调用regits方法时传入
        SysUser sysUser = new SysUser(null, username, userPwd);
        int rows = userService.regist(sysUser);
        //3根据注册结果（成功  失败）做页面跳转
        if (rows > 0){
            resp.sendRedirect("/registSuccess.html");
        }else {
            resp.sendRedirect("/registFail.html");
        }

    }
}
