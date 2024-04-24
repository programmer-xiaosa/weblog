package com.quanxiaoha.weblog.search.index;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024-03-27 17:57
 * @Version: v1.0.0
 * @Description: TODO
 **/
public interface ArticleIndex {
    /**
     * 索引名称
     */
    String NAME = "article";

    // --------------------- 文档字段 ---------------------
    String COLUMN_ID = "id";

    String COLUMN_TITLE = "title";

    String COLUMN_COVER = "cover";

    String COLUMN_SUMMARY = "summary";

    String COLUMN_CONTENT = "content";

    String COLUMN_CREATE_TIME = "createTime";
}
