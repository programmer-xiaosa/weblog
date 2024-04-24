package com.quanxiaoha.weblog.web.model.vo.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024/4/3 20:40
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublishCommentReqVO {

    /**
     * 头像
     */
    private String avatar;

    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String mail;

    /**
     * 网址
     */
    private String website;

    @NotBlank(message = "路由地址不能为空")
    private String routerUrl;

    @NotBlank(message = "评论内容不能为空")
    @Length(min = 1, max = 120, message = "评论内容需大于 1 小于 120 字符")
    private String content;

    /**
     * 回复的评论 ID
     */
    private Long replyCommentId;

    /**
     * 父评论 ID
     */
    private Long parentCommentId;

}

