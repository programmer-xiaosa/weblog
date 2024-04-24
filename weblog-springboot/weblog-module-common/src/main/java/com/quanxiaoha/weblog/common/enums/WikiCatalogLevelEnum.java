package com.quanxiaoha.weblog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024-04-01 12:23
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum WikiCatalogLevelEnum {

    // 一级目录
    ONE(1),
    // 二级目录
    TWO(2);

    private Integer value;

}