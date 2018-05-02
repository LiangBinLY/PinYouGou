package com.pinyougou.sellergoods.service.impl;
import com.pinyougou.entity.PageResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojogroup.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.sellergoods.service.SpecificationService;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;

	@Autowired
	private TbSpecificationOptionMapper optionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		//插入规格表
		TbSpecification tbspecification = specification.getSpecification();
		specificationMapper.insert(tbspecification);
		//获取规格表中的主键id作为规格表的 外键;
		List<TbSpecificationOption> tbSpecificationOptions = specification.getSpecificationOptionsList();
		for (TbSpecificationOption option: tbSpecificationOptions ){
			option.setSpecId(tbspecification.getId());
			optionMapper.insert(option);

		}

	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){
		//修改规格
		TbSpecification specification1 = specification.getSpecification();
		specificationMapper.updateByPrimaryKey(specification1);
		//修改规格项
		  //删除该规格下的所有规格项,然后再添加
		TbSpecificationOptionExample example=new TbSpecificationOptionExample();
		TbSpecificationOptionExample.Criteria criteria = example.createCriteria().andSpecIdEqualTo(specification1.getId());
		optionMapper.deleteByExample(example);
		//添加
		List<TbSpecificationOption> optionsList = specification.getSpecificationOptionsList();
		for (TbSpecificationOption option:optionsList){
			option.setSpecId(specification1.getId());
			optionMapper.insert(option);
		}



	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
		//查询出规格
		TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
		//查询出规格项

		TbSpecificationOptionExample example=new TbSpecificationOptionExample();
		TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
		 criteria.andSpecIdEqualTo(id);
		List<TbSpecificationOption> options = optionMapper.selectByExample(example);

		Specification specification=new Specification();
		specification.setSpecification(tbSpecification);
		specification.setSpecificationOptionsList(options);


		return specification;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			TbSpecificationOptionExample.Criteria criteria = example.createCriteria().andSpecIdEqualTo(id);
			optionMapper.deleteByExample(example);
			specificationMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

    @Override
    public List<Map> findSpecification() {
		List<TbSpecification> tbSpecificationList = specificationMapper.selectByExample(null);
		List<Map> listMap=new ArrayList<>();
		for(TbSpecification tbSpecification:tbSpecificationList){
			Map map=new HashMap();
			map.put("id",tbSpecification.getId());
			map.put("text",tbSpecification.getSpecName());
			listMap.add(map);
		}
		return listMap;
    }

}
