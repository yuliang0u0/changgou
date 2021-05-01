package com.changgou.goods.service.impl;

import com.changgou.goods.dao.SpecMapper;
import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.SpecService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author yuliang0u0
 * @create 2021-04-30 22:09
 */
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public PageInfo<Spec> findPage(Spec spec, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        Example example = createExample(spec);
        List<Spec> specs = specMapper.selectByExample(example);
        //执行搜索
        return new PageInfo<>(specs);
    }

    @Override
    public PageInfo<Spec> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<Spec> specs = specMapper.selectAll();
        return new PageInfo<>(specs);
    }

    @Override
    public List<Spec> findList(Spec spec) {
        Example example = createExample(spec);
        return specMapper.selectByExample(example);
    }

    /**
     * 构造查询条件
     *
     * @param spec
     * @return
     */
    private Example createExample(Spec spec) {
        Example example = new Example(Spec.class);
        Example.Criteria criteria = example.createCriteria();
        if (spec != null) {
            // ID
            if (!StringUtils.isEmpty(spec.getId())) {
                criteria.andEqualTo("id", spec.getId());
            }
            // 名称
            if (!StringUtils.isEmpty(spec.getName())) {
                criteria.andLike("name", "%" + spec.getName() + "%");
            }
            // 规格选项
            if (!StringUtils.isEmpty(spec.getOptions())) {
                criteria.andEqualTo("options", spec.getOptions());
            }
            // 排序
            if (!StringUtils.isEmpty(spec.getSeq())) {
                criteria.andEqualTo("seq", spec.getSeq());
            }
            // 模板ID
            if (!StringUtils.isEmpty(spec.getTemplateId())) {
                criteria.andEqualTo("templateId", spec.getTemplateId());
            }
        }
        return example;
    }

    @Override
    public void delete(Integer id) {
        // 查询规格
        Spec spec = specMapper.selectByPrimaryKey(id);
        // 变更模板数量
        updateSpecNum(spec, -1);
        // 删除指定规格
        specMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改模板统计数据
     *
     * @param spec:操作的模板
     * @param count:变更的数量
     */
    public void updateSpecNum(Spec spec, int count) {
        // 修改模板数量统计
        Template template = templateMapper.selectByPrimaryKey(spec.getTemplateId());
        template.setSpecNum(template.getSpecNum() + count);
        templateMapper.updateByPrimaryKeySelective(template);
    }

    @Override
    public void update(Spec spec) {
        specMapper.updateByPrimaryKey(spec);
    }

    @Override
    public void add(Spec spec) {
        specMapper.insert(spec);
        updateSpecNum(spec,1);
    }

    @Override
    public Spec findById(Integer id) {
        return specMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Spec> findAll() {
        return specMapper.selectAll();
    }
}
