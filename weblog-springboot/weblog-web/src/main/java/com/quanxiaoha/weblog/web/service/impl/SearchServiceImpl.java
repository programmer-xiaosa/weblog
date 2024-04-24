package com.quanxiaoha.weblog.web.service.impl;

import com.google.common.collect.Lists;
import com.quanxiaoha.weblog.common.utils.PageResponse;
import com.quanxiaoha.weblog.common.utils.Response;
import com.quanxiaoha.weblog.search.LuceneHelper;
import com.quanxiaoha.weblog.search.config.LuceneProperties;
import com.quanxiaoha.weblog.search.index.ArticleIndex;
import com.quanxiaoha.weblog.web.model.vo.search.SearchArticlePageListReqVO;
import com.quanxiaoha.weblog.web.model.vo.search.SearchArticlePageListRspVO;
import com.quanxiaoha.weblog.web.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.StringReader;
import java.text.ParseException;
import java.util.List;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024-03-27 18:12
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

    @Autowired
    private LuceneProperties luceneProperties;
    @Autowired
    private LuceneHelper luceneHelper;

    @Override
    public Response searchArticlePageList(SearchArticlePageListReqVO searchArticlePageListReqVO) {
        int current = Math.toIntExact(searchArticlePageListReqVO.getCurrent());
        int size = Math.toIntExact(searchArticlePageListReqVO.getSize());

        // 文章索引存放目录
        String articleIndexDir = luceneProperties.getIndexDir() + File.separator + ArticleIndex.NAME;
        // 查询关键词
        String word = searchArticlePageListReqVO.getWord();

        // 想要搜索的文档字段（这里指定对文章标题、摘要进行检索，任何一个字段包含该关键词，都会被搜索到）
        String[] columns = {ArticleIndex.COLUMN_TITLE, ArticleIndex.COLUMN_SUMMARY};
        // 查询总记录数
        long total = luceneHelper.searchTotal(ArticleIndex.NAME, word, columns);

        // 执行搜索（分页查询）
        List<Document> documents = luceneHelper.search(ArticleIndex.NAME, word, columns, current, size);

        // 若未查询到相关文档，只接 return
        if (CollectionUtils.isEmpty(documents)) {
            return PageResponse.success(total, current, size, null);
        }

        // ======================== 开始关键词高亮处理 ========================
        // 中文分析器
        Analyzer analyzer = new SmartChineseAnalyzer();
        QueryParser parser = new QueryParser(ArticleIndex.COLUMN_TITLE, analyzer);
        Query query = null;
        try {
            query = parser.parse(word);
        } catch (org.apache.lucene.queryparser.classic.ParseException e) {
            log.error("解析关键词错误:", e);
        }

        // 创建高亮器
        SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<span style=\"color: #f73131\">", "</span>");
        Highlighter highlighter = new Highlighter(formatter, new QueryScorer(query));

        // 返参 VO
        List<SearchArticlePageListRspVO> vos = Lists.newArrayList();
        // 遍历查询到的文档，进行关键词高亮处理
        documents.forEach(document -> {
            try {
                // 文章标题
                String title = document.get(ArticleIndex.COLUMN_TITLE);

                // 获取高亮的片段
                TokenStream tokenStream = analyzer.tokenStream(ArticleIndex.COLUMN_TITLE, new StringReader(title));
                String titleFragment = highlighter.getBestFragment(tokenStream, title);

                // 如果没有匹配到关键词，则返回原始文本
                String highlightedTitle = StringUtils.isNoneBlank(titleFragment) ? titleFragment : title;

                String id = document.get(ArticleIndex.COLUMN_ID);
                String cover = document.get(ArticleIndex.COLUMN_COVER);
                String createTime = document.get(ArticleIndex.COLUMN_CREATE_TIME);
                String summary = document.get(ArticleIndex.COLUMN_SUMMARY);

                // 组装 VO
                SearchArticlePageListRspVO vo = SearchArticlePageListRspVO.builder()
                        .id(Long.valueOf(id))
                        .title(highlightedTitle)
                        .summary(summary)
                        .cover(cover)
                        .createDate(createTime)
                        .build();

                vos.add(vo);
            } catch (Exception e) {
                log.error("文档转换错误: ", e);
            }
        });

        return PageResponse.success(total, current, size, vos);
    }
}