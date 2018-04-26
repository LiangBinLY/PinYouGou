package com.pinyougou.pojogroup;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiangBin
 * @version 1.0
 * @description com.pinyougou.pojogroup
 * @date 2018/4/26
 */
public class Specification implements Serializable {

    private TbSpecification specification;    //规格

    private List<TbSpecificationOption> specificationOptionsList;    //规格选项列表

    public TbSpecification getSpecification() {
        return specification;
    }

    public void setSpecification(TbSpecification specification) {
        this.specification = specification;
    }

    public List<TbSpecificationOption> getSpecificationOptionsList() {
        return specificationOptionsList;
    }

    public void setSpecificationOptionsList(List<TbSpecificationOption> specificationOptionsList) {
        this.specificationOptionsList = specificationOptionsList;
    }
}
