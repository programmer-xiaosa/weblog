package com.quanxiaoha.weblog.web.model.vo.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 木萨·塔布提
 * @Date: 2024/3/28 23:18
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindTagListReqVO {

    /**
     * 展示数量
     */
    private Long size;

}
