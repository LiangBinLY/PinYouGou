package com.pinyougou.sellergoods.service;

import com.pinyougou.entity.PageResult;
import com.pinyougou.pojo.TbBrand;

import java.util.List;

/**
 * @author LiangBin
 * @version 1.0
 * @description com.pinyougou.sellergoods.service
 * @date 2018/4/22
 */
public interface BrandService {
    public List<TbBrand>findAll();
    public PageResult findPage(int pageNum,int pageSize);

    //增加Brand,增加品牌;
    public void add(TbBrand tbBrand);

    //修改品牌,第一步先获取品牌
    public TbBrand findOne(Long id);

    //修改品牌第二步存入修改的值;
    public void update(TbBrand tbBrand);

    //删除品牌
    public void delete(Long[] ids);

    //搜索查询
    public PageResult findPage(TbBrand tbBrand,int pageNum,int pageSize);
}
