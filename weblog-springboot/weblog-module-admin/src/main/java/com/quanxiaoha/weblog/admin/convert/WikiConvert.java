package com.quanxiaoha.weblog.admin.convert;

import com.quanxiaoha.weblog.admin.model.vo.wiki.FindWikiPageListRspVO;
import com.quanxiaoha.weblog.common.domain.dos.WikiDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024-04-01 13:06
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Mapper
public interface WikiConvert {
    /**
     * 初始化 convert 实例
     */
    WikiConvert INSTANCE = Mappers.getMapper(WikiConvert.class);

    /**
     * WikiDO -> FindWikiPageListRspVO
     * @param bean
     * @return
     */
    @Mapping(target = "isTop", expression = "java(bean.getWeight() > 0)")
    FindWikiPageListRspVO convertDO2VO(WikiDO bean);

}
