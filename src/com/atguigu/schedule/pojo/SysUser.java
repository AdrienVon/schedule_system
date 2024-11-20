package com.atguigu.schedule.pojo;

/*
* 1 实体类的类名和表格名称应该对应
* 2 实体类的属性名应该和表格的列名对应
* 3 每个属性都必需是私有的
* 4 每个属性都应该具备getter   setter
* 5 必须具备无参构造器
* 6 应该实现序列化接口（缓存 分布式顶目数据传递可能会将对象序列化）
* 7 应该重写类的hashcode 和equals方法
* 8 toString是否重写都可以
*
*
*
* 使用Lombok帮助我们生成这些内容getter setter全参构造无参构造equals hashcode
*
* */

import lombok.*;

import java.io.Serializable;
import java.util.Objects;


@AllArgsConstructor //添加全参构造
@NoArgsConstructor  //添加无参构造
//@Getter
//@Setter
//@EqualsAndHashCode
@Data   //添加了getter setter hashcode equal
public class  SysUser implements Serializable {
    private Integer uid;
    private String username;
    private String userPwd;

}
