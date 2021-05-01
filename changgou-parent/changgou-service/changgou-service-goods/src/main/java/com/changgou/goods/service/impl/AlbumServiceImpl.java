package com.changgou.goods.service.impl;

import com.changgou.goods.dao.AlbumMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author yuliang0u0
 * @create 2021-04-30 20:08
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;


    @Override
    public PageInfo<Album> findPage(Album album, int page, int size) {
        PageHelper.startPage(page,size);
        // 构造查询条件
        Example example = createExample(album);
        List<Album> albums = albumMapper.selectByExample(example);
        return new PageInfo<>(albums);
    }

    @Override
    public PageInfo<Album> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<Album> albums = albumMapper.selectAll();
        return new PageInfo<>(albums);
    }

    @Override
    public List<Album> findList(Album album) {
        // 构造查询条件
        Example example = createExample(album);
        // 根据构造的条件查询
        return albumMapper.selectByExample(example);
    }

    /**
     * 构造查询条件
     *
     * @param album
     * @return
     */
    public Example createExample(Album album) {
        Example example = new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();

        if (album != null) {
            // 编号
            if (!StringUtils.isEmpty(album.getId())) {
                criteria.andEqualTo("id", album.getId());
            }
            // 相册名称
            if (!StringUtils.isEmpty(album.getTitle())) {
                criteria.andLike("title", "%" + album.getTitle() + "%");
            }
            // 相册封面
            if (!StringUtils.isEmpty(album.getImage())) {
                criteria.andEqualTo("image", album.getImage());
            }
            // 图片列表
            if (!StringUtils.isEmpty(album.getImageItems())) {
                criteria.andEqualTo("imageItems", album.getImageItems());
            }
        }
        return example;
    }

    @Override
    public void deleteById(Long id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Album album) {
        albumMapper.updateByPrimaryKeySelective(album);
    }

    @Override
    public void add(Album album) {
        albumMapper.insert(album);
    }

    @Override
    public Album findById(Long id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }
}
