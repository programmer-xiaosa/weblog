package com.quanxiaoha.weblog.search.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024-03-27 17:56
 * @Version: v1.0.0
 * @Description: TODO
 **/
@ConfigurationProperties(prefix = "lucene")
@Component
@Data
public class LuceneProperties {
    /**
     * 索引存放的文件夹
     */
    private String indexDir;
}