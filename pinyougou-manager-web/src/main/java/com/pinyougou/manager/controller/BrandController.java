package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.PageResult;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiangBin
 * @version 1.0
 * @description com.pinyougou.manager.controller
 * @date 2018/4/22
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }


    //分页查询数据
    @RequestMapping("/findPage")
    public PageResult findPage(int pageName, int pageSize){

        return brandService.findPage(pageName,pageSize);
    }

}