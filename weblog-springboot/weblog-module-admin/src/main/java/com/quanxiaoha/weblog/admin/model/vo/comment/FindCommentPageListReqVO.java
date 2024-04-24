package com.quanxiaoha.weblog.admin.model.vo.comment;

import com.quanxiaoha.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024/4/6 20:48
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询评论分页数据入参 VO")
public class FindCommentPageListReqVO extends BasePageQuery {

    /**
     * 路由地址
     */
    private String routerUrl;

    /**
     * 发布的起始日期
     */
    private LocalDate startDate;

    /**
     * 发布的结束日期
     */
    private LocalDate endDate;

    /**
     * 状态
     */
    private Integer status;
}