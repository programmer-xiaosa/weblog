package com.quanxiaoha.weblog.web.model.vo.wiki;

import com.quanxiaoha.weblog.web.model.vo.article.FindPreNextArticleRspVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024-04-01 18:43
 * @Version: v1.0.0
 * @Description: TODO
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindWikiArticlePreNextRspVO {
    /**
     * 上一篇文章
     */
    private FindPreNextArticleRspVO preArticle;
    /**
     * 下一篇文章
     */
    private FindPreNextArticleRspVO nextArticle;

}