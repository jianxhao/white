package com.white.exchange.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xh
 * @create 2023-08-20  22:18
 */
@TableName("white_role_permission")
@Data
public class RolePermission {

    @TableField(exist = false)
    private final static Long serialVersionID = 1L;

    @TableId
    private Long id;

    private Long roleId;

    private Long permissionId;

    private String note;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
