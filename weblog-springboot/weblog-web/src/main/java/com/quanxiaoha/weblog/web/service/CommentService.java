package com.quanxiaoha.weblog.web.service;

import com.quanxiaoha.weblog.common.utils.Response;
import com.quanxiaoha.weblog.web.model.vo.comment.FindCommentListReqVO;
import com.quanxiaoha.weblog.web.model.vo.comment.FindQQUserInfoReqVO;
import com.quanxiaoha.weblog.web.model.vo.comment.PublishCommentReqVO;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024/4/3 19:54
 * @Version: v1.0.0
 * @Description: TODO
 **/
public interface CommentService {

    /**
     * 根据 QQ 号获取用户信息
     * @param findQQUserInfoReqVO
     * @return
     */
    Response findQQUserInfo(FindQQUserInfoReqVO findQQUserInfoReqVO);

    /**
     * 发布评论
     * @param publishCommentReqVO
     * @return
     */
    Response publishComment(PublishCommentReqVO publishCommentReqVO);

    /**
     * 查询页面所有评论
     * @param findCommentListReqVO
     * @return
     */
    Response findCommentList(FindCommentListReqVO findCommentListReqVO);

}
