package org.qqhru.hmpt.controller;

import lombok.extern.slf4j.Slf4j;
import org.qqhru.hmpt.dto.EmpDto;
import org.qqhru.hmpt.pojo.Emp;
import org.qqhru.hmpt.service.EmpService;
import org.qqhru.hmpt.vo.PageResult;
import org.qqhru.hmpt.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 员工分页查询
     */
    @GetMapping
    public ResultVo findAll(EmpDto empDto) {
        log.info("员工分页查询: {}", empDto);
        PageResult pageResult = empService.findByPage(empDto);
        return ResultVo.success(pageResult);
    }

    /**
     * 新增员工
     */
    @PostMapping
    public ResultVo save(@RequestBody Emp emp) {
        log.info("新增员工: {}", emp);
        empService.save(emp);
        return ResultVo.success();
    }

    /**
     * 批量删除员工
     * @param ids ID列表，逗号分隔
     */
    @DeleteMapping("/{ids}")
    public ResultVo delete(@PathVariable String ids) {
        log.info("批量删除员工: {}", ids);
        Integer[] idArr = Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        empService.delete(idArr);
        return ResultVo.success();
    }

    /**
     * 根据ID查询员工
     */
    @GetMapping("/{id}")
    public ResultVo findById(@PathVariable Integer id) {
        log.info("根据ID查询员工: {}", id);
        Emp emp = empService.findById(id);
        return ResultVo.success(emp);
    }

    /**
     * 修改员工
     */
    @PutMapping
    public ResultVo update(@RequestBody Emp emp) {
        log.info("修改员工: {}", emp);
        empService.update(emp);
        return ResultVo.success();
    }
}
