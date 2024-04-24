package com.quanxiaoha.weblog.admin.model.vo.wiki;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024-04-01 15:47
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateWikiCatalogItemReqVO {

    /**
     * 目录 ID
     */
    @NotNull(message = "目录 ID 不能为空")
    private Long id;

    private Long articleId;

    @NotBlank(message = "目录标题不能为空")
    private String title;

    private Integer sort;

    private Integer level;

    /**
     * 子目录
     */
    @Valid
    private List<UpdateWikiCatalogItemReqVO> children;

}