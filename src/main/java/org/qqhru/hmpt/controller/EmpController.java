package org.qqhru.hmpt.controller;

import org.apache.ibatis.annotations.Insert;
import org.qqhru.hmpt.dto.EmpDto;
import org.qqhru.hmpt.pojo.Emp;
import org.qqhru.hmpt.service.EmpService;
import org.qqhru.hmpt.vo.PageResult;
import org.qqhru.hmpt.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //表明身份
public class EmpController {

    @Autowired //通过springboot实例化对象后 注入到此处 相当于使用创建好的对象
    private EmpService empService;//等效原来 new DeptServiceImpl();


    /**
     * 该接口用于员工列表数据查询
     * 参数格式：queryString  真正数据参数格式:
     * http://localhost:90/emps?key=value&key=value&key=value
     * http://localhost:90/emps?name=张&gender=1&begin=2007-09-01&end=2022-09-01&page=1&pageSize=10\
     * 如果是queryString的传递参数格式 获取方式有两种
     * 1.方式1 : 普通获得 直接在参数列表上直接属性 属性即可
     * 2.方式2 : 对象获得 书写一个对象 将参数列表上的属性放在对象中 直接使用对象接收即可
     */
    @GetMapping("/emps")
    public ResultVo findAll(EmpDto empDto){
        System.out.println("------->>>>>>>>>>>>>>empDto :" + empDto);
        PageResult pageResult = empService.findByPage(empDto);
        //返回数据
        return ResultVo.success(pageResult);
    }


    /**
     * 员工的保存  没有写判断
     * @return
     */
    @PostMapping("/emps")
    public ResultVo save(@RequestBody Emp emp){
        System.err.println(emp);

        empService.save(emp);
        //返回数据
        return ResultVo.success();
    }
    //根据id查询删除员工
    @DeleteMapping("/emps/{ids}")
    public ResultVo delete(@PathVariable("ids") String ids){
        String[] idArr = ids.split(",");
        Integer[] ids2 = new Integer[idArr.length];
        for (int i = 0; i < idArr.length; i++) {
            ids2[i] = Integer.parseInt(idArr[i]);
        }
        empService.delete(ids2);
        //返回数据
        return ResultVo.success();
    }

    //根据id查询员工
    @GetMapping("/emps/{id}")
    public ResultVo findById(@PathVariable("id") Integer id){
        Emp emp = empService.findById(id);
        //返回数据
        return ResultVo.success(emp);
    }
    //更新员工
    @PutMapping("/emps")
    public ResultVo update(@RequestBody Emp emp){
        empService.update(emp);
        //返回数据
        return ResultVo.success();
    }

}
