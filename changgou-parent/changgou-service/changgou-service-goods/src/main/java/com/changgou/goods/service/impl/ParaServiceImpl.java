package com.changgou.goods.service.impl;

import com.changgou.goods.dao.ParaMapper;
import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.ParaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author yuliang0u0
 * @create 2021-04-30 22:38
 */
@Service
public class ParaServiceImpl implements ParaService {

    @Autowired
    private ParaMapper paraMapper;

    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public PageInfo<Para> findPage(Para para, int page, int size) {
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(para);
        //执行搜索
        return new PageInfo<Para>(paraMapper.selectByExample(example));
    }

    @Override
    public PageInfo<Para> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Para>(paraMapper.selectAll());
    }

    @Override
    public List<Para> findList(Para para) {
        //构建查询条件
        Example example = createExample(para);
        //根据构建的条件查询数据
        return paraMapper.selectByExample(example);
    }

    private Example createExample(Para para) {
        Example example=new Example(Para.class);
        Example.Criteria criteria = example.createCriteria();
        if(para!=null){
            // id
            if(!StringUtils.isEmpty(para.getId())){
                criteria.andEqualTo("id",para.getId());
            }
            // 名称
            if(!StringUtils.isEmpty(para.getName())){
                criteria.andLike("name","%"+para.getName()+"%");
            }
            // 选项
            if(!StringUtils.isEmpty(para.getOptions())){
                criteria.andEqualTo("options",para.getOptions());
            }
            // 排序
            if(!StringUtils.isEmpty(para.getSeq())){
                criteria.andEqualTo("seq",para.getSeq());
            }
            // 模板ID
            if(!StringUtils.isEmpty(para.getTemplateId())){
                criteria.andEqualTo("templateId",para.getTemplateId());
            }
        }
        return example;
    }

    @Override
    public void delete(Integer id) {
        //根据ID查询
        Para para = paraMapper.selectByPrimaryKey(id);
        //修改模板统计数据
        updateParaNum(para,-1);

        paraMapper.deleteByPrimaryKey(id);
    }

    private void updateParaNum(Para para, int count) {
        //修改模板数量统计
        Template template = templateMapper.selectByPrimaryKey(para.getTemplateId());
        template.setParaNum(template.getParaNum()+count);
        templateMapper.updateByPrimaryKeySelective(template);
    }

    @Override
    public void update(Para para) {
        paraMapper.updateByPrimaryKey(para);
    }

    @Override
    public void add(Para para) {
        paraMapper.insert(para);

        //修改模板统计数据
        updateParaNum(para,1);
    }

    @Override
    public Para findById(Integer id) {
        return  paraMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Para> findAll() {
        return paraMapper.selectAll();
    }
}
