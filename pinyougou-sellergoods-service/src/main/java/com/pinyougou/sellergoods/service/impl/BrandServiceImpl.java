package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.entity.PageResult;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiangBin
 * @version 1.0
 * @description com.pinyougou.sellergoods.service.impl
 * @date 2018/4/22
 */
@Service
public class BrandServiceImpl implements BrandService{
    @Autowired
    private TbBrandMapper tbBrandMapper;
    @Override
    public List<TbBrand> findAll() {
        return tbBrandMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbBrand> tbBrands = tbBrandMapper.selectByExample(null);

        Page<TbBrand>page= (Page<TbBrand>) tbBrands;
        PageResult pageResult=new PageResult();
        pageResult.setTotal(page.getTotal());
        pageResult.setRows(page.getResult());
        return pageResult;
    }

    @Override
    public void add(TbBrand tbBrand) {
        tbBrandMapper.insert(tbBrand);
    }

    @Override
    public TbBrand findOne(Long id) {
        TbBrand tbBrand = tbBrandMapper.selectByPrimaryKey(id);

        return tbBrand;
    }

    @Override
    public void update(TbBrand tbBrand) {
        tbBrandMapper.updateByPrimaryKey(tbBrand);

    }

    @Override
    public void delete(Long[] ids) {
        for (Long id:ids){
            tbBrandMapper.deleteByPrimaryKey(id);
        }
        //第二种方法,直接批量删除
    }

    @Override
    public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        TbBrandExample example=new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if (brand !=null){
            if (brand.getName()!=null && brand.getName().length()>0){
                criteria.andNameLike("%"+brand.getName()+"%");
            }
            if (brand.getFirstChar()!=null && brand.getFirstChar().length()>0){
                criteria.andFirstCharEqualTo(brand.getFirstChar());
            }
        }

        List<TbBrand> tbBrands = tbBrandMapper.selectByExample(example);

        Page<TbBrand>page= (Page<TbBrand>) tbBrands;
        PageResult pageResult=new PageResult();
        pageResult.setTotal(page.getTotal());
        pageResult.setRows(page.getResult());
        return pageResult;

    }

    @Override
    public List<Map> findBrand() {
        List<TbBrand> tbBrandList = tbBrandMapper.selectByExample(null);
        List<Map> listMap=new ArrayList<>();
        for (TbBrand tbBrand:tbBrandList){
            Map map=new HashMap();
            map.put("id",tbBrand.getId());
            map.put("text",tbBrand.getName());
            listMap.add(map);
        }
        return listMap;
    }


}
