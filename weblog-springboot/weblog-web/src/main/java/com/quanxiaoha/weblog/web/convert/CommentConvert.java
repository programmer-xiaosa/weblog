package com.quanxiaoha.weblog.web.convert;

import com.quanxiaoha.weblog.common.domain.dos.CommentDO;
import com.quanxiaoha.weblog.web.model.vo.comment.FindCommentItemRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024/4/3 21:23
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
     * CommentDO -> FindCommentItemRspVO
     * @param bean
     * @return
     */
    FindCommentItemRspVO convertDO2VO(CommentDO bean);

}