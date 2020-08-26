package com.yx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author LiuG
 * @DESCRIPTION
 * @create 2020/8/21
 */
@Mapper
public interface ExecProDao {

    @Select("DECLARE @err VARCHAR(200)   " +
            " EXEC ${ProName} '${tableName}','${begin}','${end}',@err OUTPUT" +
            " select @err")
    String ExecPro(@Param("ProName") String ProName,@Param("tableName") String tableName,
                 @Param("begin") String begin,@Param("end") String end);

}
