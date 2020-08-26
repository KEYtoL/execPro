package com.yx.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author LiuG
 * @DESCRIPTION
 * @create 2020/8/21
 */
@Component
@ConfigurationProperties(prefix = "exec")
public class Tables {

//    @Value("${exec.GetDataPart1}")
    private Map<String,Map<String, Object>> names;

    public Tables() {
    }

    public Tables(Map<String, Map<String, Object>> names) {
        this.names = names;
    }

    public Map<String, Map<String, Object>> getNames() {
        return names;
    }

    public void setNames(Map<String, Map<String, Object>> names) {
        this.names = names;
    }
}

