package com.yx.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author LiuG
 * @DESCRIPTION
 * @create 2020/8/31
 */
@Data
public class ProDTO {
    private String tableName;
    private Date createTime;
    private Date outTime;

}
