package com.yx.services;

/**
 * @author LiuG
 * @DESCRIPTION
 * @create 2020/8/21
 */
public interface ExecProService {
    void ExecPro();

    String ExecProByPage(String Pro,String tableName,String Dbegin,String Dend);
}
