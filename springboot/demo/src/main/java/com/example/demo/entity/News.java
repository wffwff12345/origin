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
@AllArgsConstructor
@NoArgsConstructor
@TableName("news")
public class News {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("title")
    private String title;
    @TableField("create_time")
    private Date createTime;
    @TableField("cover")
    private String cover;
    @TableField("type")
    /* 0:封面没图片;1:封面有1张图片;3:封面有3张图片*/
    private Integer type;
    @TableField("author")
    private String author;
    @TableField("content")
    private String content;
    @TableField("channel")
    private String channel;
    @TableField("user_id")
    private Integer userId;
    
}
