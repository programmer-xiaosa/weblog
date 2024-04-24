package com.quanxiaoha.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024-04-01 12:03
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_wiki_catalog")
public class WikiCatalogDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long wikiId;

    private Long articleId;

    private String title;

    private Integer level;

    private Long parentId;

    private Integer sort;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isDeleted;
}