package org.qqhru.hmpt.controller;

import lombok.extern.slf4j.Slf4j;
import org.qqhru.hmpt.pojo.Dept;
import org.qqhru.hmpt.service.DeptService;
import org.qqhru.hmpt.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询所有部门
     */
    @GetMapping
    public ResultVo findAll() {
        log.info("查询所有部门数据");
        List<Dept> deptList = deptService.findAll();
        return ResultVo.success(deptList);
    }

    /**
     * 根据ID删除部门
     */
    @DeleteMapping("/{id}")
    public ResultVo deleteById(@PathVariable Integer id) {
        log.info("根据ID删除部门: {}", id);
        deptService.deleteById(id);
        return ResultVo.success();
    }

    /**
     * 新增部门
     */
    @PostMapping
    public ResultVo save(@RequestBody Dept dept) {
        log.info("新增部门: {}", dept);
        if (dept.getName() != null && dept.getName().length() > 10) {
            return ResultVo.error("部门名称过长,不能超过十位");
        }
        deptService.save(dept);
        return ResultVo.success();
    }

    /**
     * 根据ID查询部门
     */
    @GetMapping("/{id}")
    public ResultVo findById(@PathVariable Integer id) {
        log.info("根据ID查询部门: {}", id);
        Dept dept = deptService.findById(id);
        return ResultVo.success(dept);
    }

    /**
     * 修改部门
     */
    @PutMapping
    public ResultVo update(@RequestBody Dept dept) {
        log.info("修改部门: {}", dept);
        if (dept.getName() != null && dept.getName().length() > 10) {
            return ResultVo.error("部门名称过长,不能超过十位");
        }
        deptService.update(dept);
        return ResultVo.success();
    }
}
