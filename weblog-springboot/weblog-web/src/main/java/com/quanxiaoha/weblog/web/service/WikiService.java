package com.quanxiaoha.weblog.web.service;

import com.quanxiaoha.weblog.common.utils.Response;
import com.quanxiaoha.weblog.web.model.vo.wiki.FindWikiArticlePreNextReqVO;
import com.quanxiaoha.weblog.web.model.vo.wiki.FindWikiCatalogListReqVO;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024-04-01 18:25
 * @Version: v1.0.0
 * @Description: TODO
 **/
public interface WikiService {
    /**
     * 获取知识库
     * @return
     */
    Response findWikiList();

    /**
     * 获取知识库目录
     * @param findWikiCatalogListReqVO
     * @return
     */
    Response findWikiCatalogList(FindWikiCatalogListReqVO findWikiCatalogListReqVO);

    /**
     * 获取上下页
     * @param findWikiArticlePreNextReqVO
     * @return
     */
    Response findArticlePreNext(FindWikiArticlePreNextReqVO findWikiArticlePreNextReqVO);
}
