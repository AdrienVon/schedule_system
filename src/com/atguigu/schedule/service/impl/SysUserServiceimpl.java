package com.atguigu.schedule.service.impl;

import com.atguigu.schedule.dao.SysUserDao;
import com.atguigu.schedule.dao.impl.SysUserDaoImpl;
import com.atguigu.schedule.pojo.SysUser;
import com.atguigu.schedule.service.SysUserService;
import com.atguigu.schedule.util.MD5Util;

import java.sql.SQLException;

public class SysUserServiceimpl implements SysUserService {
    private SysUserDao userDao = new SysUserDaoImpl();
    @Override
    public int regist(SysUser sysUser) throws SQLException {
        //将用户明文密码转换为密文
        sysUser.setUserPwd(MD5Util.encrypt(sysUser.getUserPwd()));
        //调用DAO层的方法  将sysUser信息存入数据库
        return userDao.addSysUser(sysUser);

    }

    @Override
    public SysUser findByUsername(String username) throws SQLException {
        return userDao.findByUsername(username);
    }
}
