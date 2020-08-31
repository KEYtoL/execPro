package com.yx.controller;

import com.yx.commons.RestResult;
import com.yx.dto.ProDTO;
import com.yx.dto.TableDTO;
import com.yx.entity.Tables;
import com.yx.services.ExecProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author LiuG
 * @DESCRIPTION
 * @create 2020/8/27
 */
@Controller
public class ExecController {
    @Autowired
    ExecProService execProService;
    @Autowired
    Tables tables;
    private static SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取配置中的存储过程及表名
     */

    @RequestMapping("/getList")
    @ResponseBody
    public RestResult<List<TableDTO>> getProsName(){
        try {
            Map<String, Map<String, Object>> Pros=tables.getNames();
            Set<String> prosName = Pros.keySet();
            List<TableDTO> TableDTOs = new ArrayList<>();
            for (String proName : prosName) {
                Map<String, Object> tabelNames = Pros.get(proName);
                Set<String> names = tabelNames.keySet();
                for (String name : names) {
                    TableDTOs.add( new TableDTO(proName,name,"",""));
                }
            }
            return  RestResult.success(TableDTOs);
        } catch (Exception e) {
            return RestResult.failed(e.getMessage());
        }
    }

    @RequestMapping("/getTableNames")
    @ResponseBody
    public RestResult<List<String>> getTableNames(){
        try {
            Map<String, Map<String, Object>> Pros=tables.getNames();
            Set<String> prosName = Pros.keySet();
            List<String> tabels = new ArrayList<>();
            for (String proName : prosName) {
                Map<String, Object> tabelNames = Pros.get(proName);
                Set<String> names = tabelNames.keySet();
                for (String name : names) {
                    tabels.add(name);
                }
            }

            return  RestResult.success(tabels);
        } catch (Exception e) {
            return RestResult.failed(e.getMessage());
        }
    }

    @RequestMapping("/execPro")
    @ResponseBody
    public RestResult<String> execPro(ProDTO proDTO){
        try {
            if (proDTO==null||StringUtils.isEmpty(proDTO.getTableName())){
                return RestResult.failed("请传入相关数据");
            }
            String begin = simpleDateFormat.format(proDTO.getCreateTime());
            String end = simpleDateFormat.format(proDTO.getOutTime());
            Map<String, Map<String, Object>> Pros=tables.getNames();
            Set<String> prosName = Pros.keySet();
            List<TableDTO> TableDTOs = new ArrayList<>();
            for (String proName : prosName) {
                Map<String, Object> tabelNames = Pros.get(proName);
                Set<String> names = tabelNames.keySet();
                for (String name : names) {
                    if (proDTO.getTableName().equals("全部")){
                        TableDTOs.add(new TableDTO(proName,name,"DECLARE @err VARCHAR(200)   " +
                                " EXEC "+proName+" '"+name+"','"+begin+"','"+
                                end+"',@err OUTPUT" +" select @err",
                                execProService.ExecProByPage(proName, name, begin, end)
                               ));
//
                    }else if(proDTO.getTableName().equals(name)){
                        TableDTOs.add(new TableDTO(proName,name,"DECLARE @err VARCHAR(200)   " +
                                " EXEC "+proName+" '"+name+"','"+begin+"','"+
                                end+"',@err OUTPUT" +" select @err",
                                execProService.ExecProByPage(proName, name, begin, end)
                                ));
//
                        return RestResult.success(TableDTOs);
                    }
                }
            }
            return RestResult.success(TableDTOs);
        } catch (Exception e) {
            return RestResult.failed(e.getMessage());
        }
    }

}
