package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Book {
    @TableId(type = IdType.AUTO) //设置主键id生成策略
    private Integer id;
    private String type;
    private String name;
    private String description;
}
