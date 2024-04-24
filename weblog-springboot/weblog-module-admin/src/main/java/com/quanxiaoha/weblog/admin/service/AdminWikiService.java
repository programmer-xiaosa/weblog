package com.quanxiaoha.weblog.admin.service;

import com.quanxiaoha.weblog.admin.model.vo.wiki.*;
import com.quanxiaoha.weblog.common.utils.Response;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024-04-01 12:07
 * @Version: v1.0.0
 * @Description: TODO
 **/
public interface AdminWikiService {

    /**
     * 新增知识库
     * @param addWikiReqVO
     * @return
     */
    Response addWiki(AddWikiReqVO addWikiReqVO);

    /**
     * 删除知识库
     * @param deleteWikiReqVO
     * @return
     */
    Response deleteWiki(DeleteWikiReqVO deleteWikiReqVO);

    /**
     * 知识库分页查询
     * @param findWikiPageListReqVO
     * @return
     */
    Response findWikiPageList(FindWikiPageListReqVO findWikiPageListReqVO);

    /**
     * 更新知识库置顶状态
     * @param updateWikiIsTopReqVO
     * @return
     */
    Response updateWikiIsTop(UpdateWikiIsTopReqVO updateWikiIsTopReqVO);

    /**
     * 更新知识库发布状态
     * @param updateWikiIsPublishReqVO
     * @return
     */
    Response updateWikiIsPublish(UpdateWikiIsPublishReqVO updateWikiIsPublishReqVO);

    /**
     * 更新知识库
     * @param updateWikiReqVO
     * @return
     */
    Response updateWiki(UpdateWikiReqVO updateWikiReqVO);

    /**
     * 查询知识库目录
     * @param findWikiCatalogListReqVO
     * @return
     */
    Response findWikiCatalogList(FindWikiCatalogListReqVO findWikiCatalogListReqVO);

    /**
     * 更新知识库目录
     * @param updateWikiCatalogReqVO
     * @return
     */
    Response updateWikiCatalogs(UpdateWikiCatalogReqVO updateWikiCatalogReqVO);

}
