package com.yx.services.servicesIpml;

import com.yx.dao.ExecProDao;
import com.yx.entity.Tables;
import com.yx.services.ExecProService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author LiuG
 * @DESCRIPTION
 * @create 2020/8/21
 */

@Service
@Slf4j
public class ExecProServiceIpml implements  ExecProService {
    @Autowired
    ExecProDao execProDao;
    @Autowired
    Tables tables;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private Calendar calendar = Calendar.getInstance();

    /**
     * 定时任务，提取前一天数据
     */
    @Override
    @Scheduled(cron = "${cron.time}")
    public void ExecPro() {

            Date date = new Date();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -1);
            String format = this.format.format(calendar.getTime());
            log.info("info:开始执行"+format+"全民健康平台提取每日数据任务");
            String begin  = format + " 00:00:00";
            String end = format + " 23:59:59";

            Map<String, Map<String, Object>> names = tables.getNames();
            Set<String> proNames = names.keySet();
            int i = 1;
            for (String proName : proNames) {
                Map<String, Object> table = names.get(proName);
                Set<String> tableNames = table.keySet();
                int j =1;
                for (String tableName : tableNames) {
                    String err = "";
                    //执行存储过程
                    try {
                    err = execProDao.ExecPro(proName, tableName, begin, end);
                    } catch (Exception e) {
                        log.error("error"+e.getMessage());
                    }

                    if(StringUtils.isEmpty(err)){
                        log.info("info：第"+i+"个存储工程|"+proName+"，第"+j+"张表|"+tableName+"，提取数据任务成功");
                    }else {
                        log.warn("warn：第"+i+"个存储工程|"+proName+"，第"+j+"张表|"+tableName+"，提取数据任务失败");
                        log.warn("warn:失败原因："+err);
                    }
                    j++;
                }
                i++;
            }

            log.info("info:"+format+"全民健康平台提取每日数据任务结束");

    }


}