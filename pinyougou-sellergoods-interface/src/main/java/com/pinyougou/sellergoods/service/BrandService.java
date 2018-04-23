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
}
