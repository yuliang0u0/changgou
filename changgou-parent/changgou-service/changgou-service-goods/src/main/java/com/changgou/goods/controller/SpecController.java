package com.changgou.goods.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.service.SpecService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yuliang0u0
 * @create 2021-04-30 22:32
 */
@RestController
@RequestMapping("/spec")
@CrossOrigin
public class SpecController {

    @Autowired
    private SpecService specService;

    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Spec>> findPage(@RequestBody(required = false) Spec spec,
                                           @PathVariable int page, @PathVariable int size) {
        PageInfo<Spec> pageInfo = specService.findPage(spec, page, size);
        return new Result<>(true, StatusCode.OK, "分页条件查询规格成功", pageInfo);
    }

    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Spec>> findPage(@PathVariable int page, @PathVariable int size) {
        PageInfo<Spec> pageInfo = specService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "分页查询规格成功", pageInfo);
    }

    @PostMapping(value = "/search")
    public Result<List<Spec>> findList(@RequestBody(required = false) Spec spec) {
        List<Spec> list = specService.findList(spec);
        return new Result<>(true, StatusCode.OK, "条件查询规格成功", list);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        specService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Spec spec, @PathVariable Integer id) {
        // 设置主键值
        spec.setId(id);
        // 修改数据
        specService.update(spec);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @PostMapping
    public Result add(@RequestBody Spec spec) {
        specService.add(spec);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @GetMapping("/{id}")
    public Result<Spec> findById(@PathVariable Integer id) {
        //根据ID查询
        Spec spec = specService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", spec);
    }

    @GetMapping
    public Result<Spec> findAll() {
        List<Spec> list = specService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }

}
