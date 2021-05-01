package com.changgou.goods.service;

import com.changgou.goods.pojo.Album;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author yuliang0u0
 * @create 2021-04-30 20:09
 */
public interface AlbumService {

    /**
     * 条件分页查询相册
     * @param album
     * @param page
     * @param size
     * @return
     */
    PageInfo<Album> findPage(Album album, int page, int size);

    /**
     * 分页查询相册
     * @param page
     * @param size
     * @return
     */
    PageInfo<Album> findPage(int page, int size);

    /**
     * 条件查询相册
     * @param album
     * @return
     */
    List<Album> findList(Album album);

    /**
     * 根据ID删除相册
     * @param id
     */
    void deleteById(Long id);

    /**
     * 修改相册
     * @param album
     */
    void update(Album album);

    /**
     * 新增相册
     * @param album
     */
    void add(Album album);

    /**
     * 根据ID查询相册
     * @param id
     * @return
     */
    Album findById(Long id);

    /**
     * 查询所有相册
     * @return
     */
    List<Album> findAll();

}
