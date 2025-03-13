package org.qqhru.hmpt.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.qqhru.hmpt.mapper.DeptMapper;
import org.qqhru.hmpt.pojo.Dept;
import org.qqhru.hmpt.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 业务逻辑层
 */
@Service //表明身份
@Slf4j //日志 有这个注解, 一会执行代码的时候 控制台会输出一定的日志信息
public class DeptServiceImpl implements DeptService {

    @Autowired //注入并使用对象
    private DeptMapper deptMapper;
    /**
     * service 部门查询所有内容
     * @return
     */
    @Override
    public List<Dept> findAll() {
        //查询部门数据
        List<Dept> deptList = deptMapper.findAll();
        return deptList;
    }

    /**
     * 该接口用于根据ID删除部门数据
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    /**
     * 该接口用于添加部门数据
     * @param dept
     */
    @Override
    public void save(Dept dept) {
        //保存之前要将dept对象补充完整
        dept.setCreateTime(LocalDateTime.now()); //当前时间
        dept.setUpdateTime(LocalDateTime.now()); //当前时间
        //保存部门
        deptMapper.save(dept);
    }

    /**
     * 该接口用于根据ID修改部门数据
     */
    @Override
    public void update(Dept dept) {
        //更新之前要将dept对象补充完整
        dept.setUpdateTime(LocalDateTime.now()); //当前时间
        //更新部门
        deptMapper.update(dept);
    }

    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }


}
