package org.qqhru.hmpt.service;

import org.qqhru.hmpt.pojo.Dept;

import java.util.List;

public interface DeptService {
    //该接口用于部门列表数据查询
    List<Dept> findAll();

    //该接口用于根据ID删除部门数据
    void deleteById(Integer id);

    //该接口用于添加部门数据
    void save(Dept dept);

    //该接口用于修改部门数据
    void update(Dept dept);

    Dept findById(Integer id);
}
