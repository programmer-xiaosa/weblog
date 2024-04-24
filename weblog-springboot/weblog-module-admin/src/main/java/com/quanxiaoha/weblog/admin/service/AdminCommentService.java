package com.quanxiaoha.weblog.admin.service;

import com.quanxiaoha.weblog.admin.model.vo.comment.DeleteCommentReqVO;
import com.quanxiaoha.weblog.admin.model.vo.comment.ExamineCommentReqVO;
import com.quanxiaoha.weblog.admin.model.vo.comment.FindCommentPageListReqVO;
import com.quanxiaoha.weblog.common.utils.Response;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024/4/6 22:36
 * @Version: v1.0.0
 * @Description: TODO
 **/
public interface AdminCommentService {

    /**
     * 查询评论分页数据
     * @param findCommentPageListReqVO
     * @return
     */
    Response findCommentPageList(FindCommentPageListReqVO findCommentPageListReqVO);

    /**
     * 删除评论
     * @param deleteCommentReqVO
     * @return
     */
    Response deleteComment(DeleteCommentReqVO deleteCommentReqVO);

    /**
     * 评论审核
     * @param examineCommentReqVO
     * @return
     */
    Response examine(ExamineCommentReqVO examineCommentReqVO);
}
