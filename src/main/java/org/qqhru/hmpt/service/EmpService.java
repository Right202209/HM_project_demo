package org.qqhru.hmpt.service;

import org.qqhru.hmpt.dto.EmpDto;
import org.qqhru.hmpt.pojo.Dept;
import org.qqhru.hmpt.pojo.Emp;
import org.qqhru.hmpt.vo.PageResult;

import java.util.List;

public interface EmpService {
    //分页查询  返回值一会再改
    PageResult findByPage(EmpDto empDto);

    //员工保存
    void save(Emp emp);


    void delete(Integer[] ids);

    Emp findById(Integer id);

    void update(Emp emp);

    //登录代码
    String login(Emp emp);
}
