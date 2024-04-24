package com.quanxiaoha.weblog.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024-03-27 19:17
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Getter
public class DeleteArticleEvent extends ApplicationEvent {

    /**
     * 文章 ID
     */
    private Long articleId;

    public DeleteArticleEvent(Object source, Long articleId) {
        super(source);
        this.articleId = articleId;
    }
}