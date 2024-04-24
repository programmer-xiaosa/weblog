package com.quanxiaoha.weblog.web.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024/4/3 21:41
 * @Version: v1.0.0
 * @Description: TODO
 **/

@Getter
public class PublishCommentEvent extends ApplicationEvent {

    /**
     * 评论 ID
     */
    private Long commentId;

    public PublishCommentEvent(Object source, Long commentId) {
        super(source);
        this.commentId = commentId;
    }
}
