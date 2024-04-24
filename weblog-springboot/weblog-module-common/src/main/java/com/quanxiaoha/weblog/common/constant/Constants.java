package com.quanxiaoha.weblog.common.constant;

import java.time.format.DateTimeFormatter;

/**
 * @author: 木萨·塔布提
 * @url: blog.arnasoft.site
 * @date: 2023/11/13 11:22
 * @description: 全局静态变量
 **/
public interface Constants {
    /**
     * 月-日 格式
     */
    DateTimeFormatter MONTH_DAY_FORMATTER = DateTimeFormatter.ofPattern("MM-dd");

    /**
     * 年-月-日 小时-分钟-秒
     */
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
