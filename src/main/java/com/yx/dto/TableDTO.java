package com.yx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuG
 * @DESCRIPTION
 * @create 2020/8/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableDTO {

    private String execName;
    private String tableName;
    private String sql;
    private String message;
}
