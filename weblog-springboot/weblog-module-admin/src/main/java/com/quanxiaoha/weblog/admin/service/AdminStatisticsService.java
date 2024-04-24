package com.quanxiaoha.weblog.admin.service;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024/3/28 20:32
 * @Version: v1.0.0
 * @Description: TODO
 **/
public interface AdminStatisticsService {

    /**
     * 统计各分类下文章总数
     */
    void statisticsCategoryArticleTotal();

    /**
     * 统计各标签下文章总数
     */
    void statisticsTagArticleTotal();
}