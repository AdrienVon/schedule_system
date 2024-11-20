package com.atguigu.schedule.dao;

import com.atguigu.schedule.pojo.SysSchedule;

import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public interface SysScheduleDao {
    /**
     *用于向数据中增加一条日程记录
     * @param sysschedule   日程数据以SysScheduLe实体类对象形式入参
     * @return  返回影响数据库记录的行数，行数为阳说明增加失收，行数大日说明增加成功
     */
    int addSchedule(SysSchedule sysschedule) throws SQLException;

    /**
     * 查询所有用户的所有日程
     * @return  将所有日程放入一个:List<SysSchedule>集合中返回
     */
    List <SysSchedule> findAll() throws SQLException;
}
