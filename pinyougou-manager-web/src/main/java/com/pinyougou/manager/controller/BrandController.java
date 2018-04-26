package com.pinyougou.manager.controller;
import com.pinyougou.entity.PageResult;
import com.pinyougou.entity.Result;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
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

    //增加品牌
    @RequestMapping("/add")
    //angularjs传递数据是对象时默认是json类型
    public Result add(@RequestBody TbBrand tbBrand){

        try {
            brandService.add(tbBrand);
            return new Result(true,"添加品牌成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加品牌失败!");
        }
    }

    //修改品牌,第一步查询出该品牌;
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id){
        TbBrand tbBrand = brandService.findOne(id);
        return tbBrand;

    }
    //修改品牌第二步存入品牌;
    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand tbBrand){

        try {
            brandService.update(tbBrand);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败");
        }

    }

    //删除品牌
    @RequestMapping("/delete")
    public Result delete(Long[] ids){

        try {
            brandService.delete(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    //搜索品牌
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand brand,int pageNum,int pageSize){

        System.out.println("接收的参数:"+brand+"///"+pageNum+"//////"+pageSize);



        return brandService.findPage(brand,pageNum,pageSize);

    }

}