package com.quanxiaoha.weblog.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024/4/6 23:23
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Getter
public class UpdateCommentEvent extends ApplicationEvent {

    /**
     * 评论 ID
     */
    private Long commentId;

    public UpdateCommentEvent(Object source, Long commentId) {
        super(source);
        this.commentId = commentId;
    }
}

