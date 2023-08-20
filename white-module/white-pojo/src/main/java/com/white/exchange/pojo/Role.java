package com.white.exchange.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xh
 * @create 2023-08-20  21:34
 */
@TableName("white_role")
@Data
public class Role implements Serializable {

    @TableField(exist = false)
    private final static Long serialVersionID = 1L;

    @TableId
    private Long id;

    private String name;

    private String note;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
