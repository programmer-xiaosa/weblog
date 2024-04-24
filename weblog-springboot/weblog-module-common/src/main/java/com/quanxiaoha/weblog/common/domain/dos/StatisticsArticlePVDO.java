package com.quanxiaoha.weblog.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author: 木萨·塔布提
 * @url: blog.arnasoft.site
 * @date: 2023-08-22 17:01
 * @description: 每日文章 PV 访问量统计
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_statistics_article_pv")
public class StatisticsArticlePVDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private LocalDate pvDate;

    private Long pvCount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
