package com.quanxiaoha.weblog.admin.event.subscriber;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.quanxiaoha.weblog.admin.event.UpdateCommentEvent;
import com.quanxiaoha.weblog.common.domain.dos.BlogSettingsDO;
import com.quanxiaoha.weblog.common.domain.dos.CommentDO;
import com.quanxiaoha.weblog.common.domain.mapper.BlogSettingsMapper;
import com.quanxiaoha.weblog.common.domain.mapper.CommentMapper;
import com.quanxiaoha.weblog.common.enums.CommentStatusEnum;
import com.quanxiaoha.weblog.common.mail.MailHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024/4/6 23:24
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Component
@Slf4j
public class UpdateCommentSubscriber implements ApplicationListener<UpdateCommentEvent> {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private BlogSettingsMapper blogSettingsMapper;
    @Autowired
    private MailHelper mailHelper;

    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(UpdateCommentEvent event) {
        // 在这里处理收到的事件，可以是任何逻辑操作
        Long commentId = event.getCommentId();

        // 获取当前线程名称
        String threadName = Thread.currentThread().getName();

        log.info("==> threadName: {}", threadName);
        log.info("==> 评论发布事件消费成功，commentId: {}", commentId);

        CommentDO commentDO = commentMapper.selectById(commentId);
        Long replyCommentId = commentDO.getReplyCommentId();
        String nickname = commentDO.getNickname();
        String content = commentDO.getContent();
        Integer status = commentDO.getStatus();
        String mail = commentDO.getMail();
        String routerUrl = commentDO.getRouterUrl();


        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);
        String blogName = blogSettingsDO.getName();
        // 博客域名
        String domain = "https://blog.arnasoft.site";

        // 判断评论状态
        // 若审核不通过，通知发评论的用户，你的评论未被博主审核通过，原因是什么
        if (Objects.equals(status, CommentStatusEnum.EXAMINE_FAILED.getCode())
                && StringUtils.isNotBlank(mail)) {

            String reason = commentDO.getReason();
            String title = String.format("你在%s的评论未被审核通过", blogName);

            String html = String.format("<html><body>" +
                            "<h2>你的评论:</h2><p>%s</p>" +
                            "<h2>审核未通过原因:</h2><p>%s</p>" +
                            "<p><a href='%s%s' target='_blank'>查看详情</a></p>" +
                            "</body></html>",
                    content, reason, domain, routerUrl);
            mailHelper.sendHtml(mail, title, html);
        } else if (Objects.equals(status, CommentStatusEnum.NORMAL.getCode())) {
            // 如果是审核通过，通知发评论的用户，你的评论已经被博主审核通过
            String title = String.format("你在%s的评论已被审核通过", blogName);
            String html = String.format("<html><body>" +
                            "<h2>你的评论:</h2><p>%s</p>" +
                            "<p><a href='%s%s' target='_blank'>查看详情</a></p>" +
                            "</body></html>",
                    content, domain, routerUrl);
            mailHelper.sendHtml(mail, title, html);

            // 另外，还得通知被评论人，提示评论被回复了
            notifyBeCommentedUser(replyCommentId, blogName, nickname, content, domain);
        }


    }

    /**
     * 邮件通知被回复的用户
     * @param replyCommentId
     * @param blogName
     * @param nickname
     * @param content
     * @param domain
     */
    private void notifyBeCommentedUser(Long replyCommentId, String blogName, String nickname, String content, String domain) {
        if (Objects.isNull(replyCommentId))
            return;

        // 被回复的评论
        CommentDO replyCommentDO = commentMapper.selectById(replyCommentId);

        // 邮箱地址
        String to = replyCommentDO.getMail();

        // 邮箱判空
        if (StringUtils.isBlank(to))
            return;

        String routerUrl = replyCommentDO.getRouterUrl();
        String title = String.format("你在%s的评论收到了回复", blogName);

        String html = String.format("<html><body>" +
                        "<h2>你的评论:</h2><p>%s</p>" +
                        "<h2>%s 回复了你:</h2><p>%s</p>" +
                        "<p><a href='%s%s' target='_blank'>查看详情</a></p>" +
                        "</body></html>",
                replyCommentDO.getContent(), nickname, content, domain, routerUrl);
        mailHelper.sendHtml(to, title, html);
    }
}
