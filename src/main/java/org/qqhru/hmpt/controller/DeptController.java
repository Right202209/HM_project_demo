package org.qqhru.hmpt.controller;

import org.apache.ibatis.annotations.Update;
import org.qqhru.hmpt.pojo.Dept;
import org.qqhru.hmpt.service.DeptService;
import org.qqhru.hmpt.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //表明身份
public class DeptController {

    @Autowired //通过springboot实例化对象后 注入到此处 相当于使用创建好的对象
    private DeptService deptService;//等效原来 new DeptServiceImpl();
    /**
     * 该接口用于部门列表数据查询
     * @return
     */
    @GetMapping("/depts")
    public ResultVo findAll(){
        //查询所有的部门数据
        List<Dept> deptList = deptService.findAll(); //alt + 回车 生成方法
        //返回数据
        return ResultVo.success(deptList);
    }



    /**
     * 该接口用于根据ID删除部门数据
     * 路径上写 {key} 这种方式叫路径传参  {key}动态变量
     * 例如:  localhost:90/depts/1
     * 例如:  localhost:90/depts/2
     * 例如:  localhost:90/depts/3
     * 例如:  localhost:90/depts/4
     * 如果是使用路径传参 不能够直接获得数据 必须加上一个注解 @PathVariable
     * @PathVariable作用: 获得地址栏中的动态变量
     * delete from dept where id = ?
     */
    @DeleteMapping("/depts/{id}")
    public ResultVo deleteById(@PathVariable Integer id){

        deptService.deleteById(id);
        //返回数据
        return ResultVo.success();
    }


    /**
     * 该接口用于添加部门数据
     * 传递一个name属性值 部门名称
     * 传递参数的时候 :  传递的数据格式：application/json  -> json格式数据 { key:value,key:value,key:value} 的形式
     * {
     * 	"name": "教研部"
     * }
     * 如果是这种格式下 , 我们希望获得数据 并封装成对象中属性的时候 需要额外加入一个注解 @RequestBody
     * @RequestBody作用: 将json格式数据解析出来并赋值到对象中属性
     * ctrl + alt + t 快速生成代码块
     */
    @PostMapping("/depts")
    public ResultVo save(@RequestBody Dept dept){
        try {
            if(dept.getName().length()>10){
                return ResultVo.error("部门名称过长,不能超过十位");
            }
            deptService.save(dept);
            //返回数据
            return ResultVo.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.error("对不起,部门名称已经存在");
        }
    }

    /**
     *该接口用于获取部门id
     * @param id
     */
    @GetMapping("/depts/{id}")
    public ResultVo findById(@PathVariable Integer id){
        Dept dept = deptService.findById(id);
        //返回数据
        return ResultVo.success(dept);
    }

    /**
     * 该接口用于根据ID修改部门数据
     */
    @PutMapping("/depts")
    public ResultVo update(@RequestBody Dept dept){
        try {
            if(dept.getName().length()>10){
                return ResultVo.error("部门名称过长,不能超过十位");
            }
            deptService.update(dept);
            //返回数据
            return ResultVo.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.error("修改失败");
        }
    }
}
