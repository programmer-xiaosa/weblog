package com.quanxiaoha.weblog.admin.convert;

import com.quanxiaoha.weblog.admin.model.vo.comment.FindCommentPageListRspVO;
import com.quanxiaoha.weblog.common.domain.dos.CommentDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024/4/6 22:35
 * @Version: v1.0.0
 * @Description: TODO
 **/

@Mapper
public interface CommentConvert {
    /**
     * 初始化 convert 实例
     */
    CommentConvert INSTANCE = Mappers.getMapper(CommentConvert.class);

    /**
     * 将 DO 转化为 VO
     * @param bean
     * @return
     */
    FindCommentPageListRspVO convertDO2VO(CommentDO bean);

}