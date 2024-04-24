package com.quanxiaoha.weblog.web.model.vo.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024-03-27 18:12
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchArticlePageListRspVO {
    /**
     * 文章 ID
     */
    private Long id;
    /**
     * 封面
     */
    private String cover;
    /**
     * 标题
     */
    private String title;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 发布日期
     */
    private String createDate;
}

