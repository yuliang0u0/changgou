package com.changgou.goods.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import com.changgou.goods.service.impl.AlbumServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yuliang0u0
 * @create 2021-04-30 20:54
 */
@RestController
@RequestMapping("/album")
@CrossOrigin
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Album>> findPage(@RequestBody(required = false) Album album, @PathVariable int page, @PathVariable int size) {
        PageInfo<Album> pageInfo = albumService.findPage(album, page, size);
        return new Result<>(true, StatusCode.OK, "分页条件查询相册成功！", pageInfo);
    }

    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Album>> findPage(@PathVariable int page, @PathVariable int size) {
        PageInfo<Album> pageInfo = albumService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "分页查询相册成功！", pageInfo);
    }

    @PostMapping("/search")
    public Result<List<Album>> findList(@RequestBody(required = false) Album album) {
        List<Album> albums = albumService.findList(album);
        return new Result<>(true, StatusCode.OK, "条件查询相册成功！", albums);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id) {
        albumService.deleteById(id);
        return new Result(true, StatusCode.OK, "根据ID删除相册成功！");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody Album album) {
        album.setId(id);
        albumService.update(album);
        return new Result(true, StatusCode.OK, "修改相册成功！");
    }

    @PostMapping
    public Result add(@RequestBody Album album) {
        albumService.add(album);
        return new Result(true, StatusCode.OK, "新增相册成功！");
    }

    @GetMapping("/{id}")
    public Result<Album> findById(@PathVariable Long id) {
        Album album = albumService.findById(id);
        return new Result<>(true, StatusCode.OK, "根据ID查询相册成功！", album);
    }

    @GetMapping
    public Result<Album> findAll() {
        List<Album> albums = albumService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有相册成功！", albums);
    }
}
