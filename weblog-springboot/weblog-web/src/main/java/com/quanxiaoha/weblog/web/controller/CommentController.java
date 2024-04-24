package com.quanxiaoha.weblog.web.controller;

import com.quanxiaoha.weblog.common.aspect.ApiOperationLog;
import com.quanxiaoha.weblog.common.utils.Response;
import com.quanxiaoha.weblog.web.model.vo.comment.FindCommentListReqVO;
import com.quanxiaoha.weblog.web.model.vo.comment.FindQQUserInfoReqVO;
import com.quanxiaoha.weblog.web.model.vo.comment.PublishCommentReqVO;
import com.quanxiaoha.weblog.web.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Author: 木萨·塔布提
 * @Date: 2024/4/3 19:57
 * @Version: v1.0.0
 * @Description: TODO
 **/
@RestController
@RequestMapping("/comment")
@Api(tags = "评论")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/qq/userInfo")
    @ApiOperation(value = "获取 QQ 用户信息")
    @ApiOperationLog(description = "获取 QQ 用户信息")
    public Response findQQUserInfo(@RequestBody @Validated FindQQUserInfoReqVO findQQUserInfoReqVO) {
        return commentService.findQQUserInfo(findQQUserInfoReqVO);
    }

    @PostMapping("/publish")
    @ApiOperation(value = "发布评论")
    @ApiOperationLog(description = "发布评论")
    public Response publishComment(@RequestBody @Validated PublishCommentReqVO publishCommentReqVO) {
        return commentService.publishComment(publishCommentReqVO);
    }

    @PostMapping("/list")
    @ApiOperation(value = "获取页面所有评论")
    @ApiOperationLog(description = "获取页面所有评论")
    public Response findPageComments(@RequestBody FindCommentListReqVO findCommentListReqVO) {
        return commentService.findCommentList(findCommentListReqVO);
    }

}