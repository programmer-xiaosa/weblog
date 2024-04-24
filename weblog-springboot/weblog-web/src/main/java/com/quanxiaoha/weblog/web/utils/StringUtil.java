package com.quanxiaoha.weblog.web.utils;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024/4/3 19:53
 * @Version: v1.0.0
 * @Description: TODO
 **/
public class StringUtil {

    /**
     * 判断字符串是否是纯数字
     * @param str
     * @return
     */
    public static boolean isPureNumber(String str) {
        return str.matches("\\d+");
    }

}
