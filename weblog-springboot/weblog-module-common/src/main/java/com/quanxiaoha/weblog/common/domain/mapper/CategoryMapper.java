package com.quanxiaoha.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanxiaoha.weblog.common.domain.dos.CategoryDO;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author: 木萨·塔布提
 * @url: blog.arnasoft.site
 * @date: 2023-08-22 17:06
 * @description: TODO
 **/
public interface CategoryMapper extends BaseMapper<CategoryDO> {

    /**
     * 查询分类分页数据
     * @return
     */
    default Page<CategoryDO> selectPageList(long current, long size, String name, LocalDate startDate, LocalDate endDate) {
        // 分页对象(查询第几页、每页多少数据)
        Page<CategoryDO> page = new Page<>(current, size);

        // 构建查询条件
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();

        wrapper
                .like(StringUtils.isNotBlank(name), CategoryDO::getName, name.trim()) // like 模块查询
                .ge(Objects.nonNull(startDate), CategoryDO::getCreateTime, startDate) // 大于等于 startDate
                .le(Objects.nonNull(endDate), CategoryDO::getCreateTime, endDate)  // 小于等于 endDate
                .orderByDesc(CategoryDO::getCreateTime); // 按创建时间倒叙

        return selectPage(page, wrapper);
    }

    /**
     * 根据用户名查询
     * @param categoryName
     * @return
     */
    default CategoryDO selectByName(String categoryName) {
        // 构建查询条件
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CategoryDO::getName, categoryName);

        // 执行查询
        return selectOne(wrapper);
    }

    /**
     * 查询时指定数量
     * @param limit
     * @return
     */
    default List<CategoryDO> selectByLimit(Long limit) {
        return selectList(Wrappers.<CategoryDO>lambdaQuery()
                .orderByDesc(CategoryDO::getArticlesTotal) // 根据文章总数降序
                .last(String.format("LIMIT %d", limit))); // 查询指定数量
    }
}
