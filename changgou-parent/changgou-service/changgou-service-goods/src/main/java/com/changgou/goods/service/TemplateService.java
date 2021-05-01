package com.changgou.goods.service;

import com.changgou.goods.pojo.Template;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author yuliang0u0
 * @create 2021-04-30 21:43
 */
public interface TemplateService {

    /**
     * 条件分页查询Template
     * @param template
     * @param page
     * @param size
     * @return
     */
    PageInfo<Template> findPage(Template template, int page, int size);

    /***
     * 分页查询Template
     * @param page
     * @param size
     * @return
     */
    PageInfo<Template> findPage(int page, int size);

    /***
     * 条件查询Template
     * @param template
     * @return
     */
    List<Template> findList(Template template);

    /***
     * 根据ID删除Template
     * @param id
     */
    void deleteById(Integer id);

    /***
     * 修改Template数据
     * @param template
     */
    void update(Template template);

    /***
     * 新增Template
     * @param template
     */
    void add(Template template);

    /**
     * 根据ID查询Template
     *
     * @param id
     * @return
     */
    Template findById(Integer id);

    /***
     * 查询所有Template
     * @return
     */
    List<Template> findAll();

}
