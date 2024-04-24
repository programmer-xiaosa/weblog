package com.quanxiaoha.weblog.web.service;

import com.quanxiaoha.weblog.common.utils.Response;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024-03-27 16:37
 * @Version: v1.0.0
 * @Description: TODO
 **/
public interface StatisticsService {
    /**
     * 获取文章总数、分类总数、标签总数、总访问量统计信息
     * @return
     */
    Response findInfo();
}
