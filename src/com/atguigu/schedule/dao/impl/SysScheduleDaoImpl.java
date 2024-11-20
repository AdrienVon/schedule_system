package com.atguigu.schedule.dao.impl;

import com.atguigu.schedule.dao.BaseDao;
import com.atguigu.schedule.dao.SysScheduleDao;
import com.atguigu.schedule.pojo.SysSchedule;

import java.sql.SQLException;
import java.util.List;

public class SysScheduleDaoImpl extends BaseDao implements SysScheduleDao {


    @Override
    public int addSchedule(SysSchedule schedule) throws SQLException {
        String sql ="insert into sys_schedule values(DEFAULT,?,?,?)";
        int rows = baseUpdate(sql,schedule.getUid(),schedule.getTitle(),schedule.getCompleted());
        return rows;
    }

    @Override
    public List<SysSchedule> findAll() throws SQLException {
        String sql ="select sid,uid,title,completed from sys_schedule";
        List<SysSchedule> scheduleList = baseQuery(SysSchedule.class, sql);
        return scheduleList;
    }
}


