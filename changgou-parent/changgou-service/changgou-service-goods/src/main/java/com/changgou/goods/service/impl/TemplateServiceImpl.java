package com.changgou.goods.service.impl;

import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author yuliang0u0
 * @create 2021-04-30 21:43
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public PageInfo<Template> findPage(Template template, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(template);
        List<Template> templates = templateMapper.selectByExample(example);
        return new PageInfo<>(templates);
    }

    @Override
    public PageInfo<Template> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<Template> templates = templateMapper.selectAll();
        return new PageInfo<>(templates);
    }

    @Override
    public List<Template> findList(Template template) {
        Example example = createExample(template);
        return templateMapper.selectByExample(example);
    }

    /**
     * 构造查询条件
     *
     * @param template
     * @return
     */
    private Example createExample(Template template) {
        Example example = new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();
        if (example != null) {
            // ID
            if (!StringUtils.isEmpty(template.getId())) {
                criteria.andEqualTo("id", template.getId());
            }
            // 模板名称
            if (!StringUtils.isEmpty(template.getName())) {
                criteria.andLike("name", "%" + template.getName() + "%");
            }
            // 规格数量
            if (!StringUtils.isEmpty(template.getSpecNum())) {
                criteria.andEqualTo("specNum", template.getSpecNum());
            }
            // 参数数量
            if (!StringUtils.isEmpty(template.getParaNum())) {
                criteria.andEqualTo("paraNum", template.getParaNum());
            }
        }
        return example;
    }

    @Override
    public void deleteById(Integer id) {
        templateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Template template) {
        templateMapper.updateByPrimaryKeySelective(template);
    }

    @Override
    public void add(Template template) {
        templateMapper.insert(template);
    }

    @Override
    public Template findById(Integer id) {
        return templateMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Template> findAll() {
        return templateMapper.selectAll();
    }

}
