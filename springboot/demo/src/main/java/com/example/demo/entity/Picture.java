package com.example.demo.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("picture")
public class Picture {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("url")
    private String url;
    @TableField("create_time")
    private Date createTime;
    @TableField("status")
    private Integer status;
    @TableField("type")
    private Integer type;
    @TableField("user_id")
    private Integer userId;
}
