package com.quanxiaoha.weblog.web.model.vo.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024-03-27 16:19
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindStatisticsInfoRspVO {
    /**
     * 文章总数
     */
    private Long articleTotalCount;

    /**
     * 分类总数
     */
    private Long categoryTotalCount;

    /**
     * 标签总数
     */
    private Long tagTotalCount;

    /**
     * 总浏览量
     */
    private Long pvTotalCount;
}
