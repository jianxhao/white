package com.white.exchange.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xh
 * @create 2023-08-20  13:00
 */
@TableName(value = "white_user")
@Data
public class User implements Serializable {

    @TableId
    private Long id;

    private String username;

    private String password;

    private String icon;

    private String nickname;

    private String phone;

    private String note;

    private String address;

    private String gender;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableField(exist = false)
    private final static Long serialVersionUID = 1L;

}
