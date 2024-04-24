package com.quanxiaoha.weblog.admin.model.vo.wiki;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
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
@ApiModel(value = "更新知识库目录数据入参 VO")
public class UpdateWikiCatalogReqVO {

    /**
     * 知识库 ID
     */
    @NotNull(message = "知识库 ID 不能为空")
    private Long id;

    /**
     * 目录
     */
    @Valid
    private List<UpdateWikiCatalogItemReqVO> catalogs;
}