package com.changgou.goods.controller;

import com.changgou.common.entity.Result;
import com.changgou.common.entity.StatusCode;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yuliang0u0
 * @create 2021-04-30 21:55
 */
@RestController
@RequestMapping("/template")
@CrossOrigin
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Template>> findPage(@RequestBody(required = false) Template template,
                                     @PathVariable int page, @PathVariable int size) {
        PageInfo<Template> pageInfo = templateService.findPage(template, page, size);
        return new Result<>(true, StatusCode.OK, "分页条件查询模板成功！", pageInfo);
    }

    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Template>> findPage(@PathVariable int page, @PathVariable int size) {
        PageInfo<Template> pageInfo = templateService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "分页查询模板成功", pageInfo);
    }

    @PostMapping(value = "/search")
    public Result<List<Template>> findList(@RequestBody(required = false) Template template) {
        List<Template> list = templateService.findList(template);
        return new Result<>(true, StatusCode.OK, "条件查询模板成功", list);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        templateService.deleteById(id);
        return new Result(true, StatusCode.OK, "根据ID删除模板成功");
    }

    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Template template, @PathVariable Integer id) {
        template.setId(id);
        templateService.update(template);
        return new Result(true, StatusCode.OK, "根据ID修改模板成功");
    }

    @PostMapping
    public Result add(@RequestBody Template template) {
        templateService.add(template);
        return new Result(true, StatusCode.OK, "新增模板成功");
    }

    @GetMapping("/{id}")
    public Result<Template> findById(@PathVariable Integer id) {
        Template template = templateService.findById(id);
        return new Result<>(true, StatusCode.OK, "根据ID查询模板成功", template);
    }

    @GetMapping
    public Result<Template> findAll() {
        List<Template> list = templateService.findAll();
        return new Result<>(true, StatusCode.OK, "查询所有模板成功", list);
    }

}
