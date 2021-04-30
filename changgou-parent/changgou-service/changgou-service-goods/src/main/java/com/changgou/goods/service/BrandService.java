package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author yuliang0u0
 * @create 2021-03-23 18:37
 */
public interface BrandService {

    /**
     * 条件分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(Brand brand, Integer page, Integer size);

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(Integer page, Integer size);

    /**
     * 多条件查询品牌
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);

    /**
     * 根据id删除品牌
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 修改品牌
     * @param brand
     */
    void update(Brand brand);

    /**
     * 增加品牌
     * @param brand
     */
    void add(Brand brand);

    /**
     * 根据ID查询品牌
     * @param id
     * @return
     */
    Brand findById(Integer id);

    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> findAll();

}
