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
public enum ArticleTypeEnum {

    NORMAL(1, "普通"),
    WIKI(2, "收录于知识库");

    private Integer value;
    private String description;

}