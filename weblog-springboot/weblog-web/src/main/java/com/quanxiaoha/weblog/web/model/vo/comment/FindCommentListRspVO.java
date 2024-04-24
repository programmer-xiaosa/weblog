package com.quanxiaoha.weblog.web.model.vo.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024/4/3 21:20
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindCommentListRspVO {

    /**
     * 总评论数
     */
    private Integer total;

    /**
     * 评论集合
     */
    private List<FindCommentItemRspVO> comments;

}