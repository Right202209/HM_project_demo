package org.qqhru.hmpt.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.qqhru.hmpt.dto.EmpDto;
import org.qqhru.hmpt.mapper.EmpMapper;
import org.qqhru.hmpt.pojo.Emp;
import org.qqhru.hmpt.service.EmpService;
import org.qqhru.hmpt.utils.JwtUtils;
import org.qqhru.hmpt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工业务实现
 */
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageResult findByPage(EmpDto empDto) {
        // 1. 设置分页参数
        PageHelper.startPage(empDto.getPage(), empDto.getPageSize());

        // 2. 执行查询
        List<Emp> empList = empMapper.findByPage(empDto);
        Page<Emp> p = (Page<Emp>) empList;

        // 3. 封装结果
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }

    @Override
    public void delete(Integer[] ids) {
        empMapper.delete(ids);
    }

    @Override
    public Emp findById(Integer id) {
        return empMapper.findById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public String login(Emp emp) {
        if (StrUtil.isEmpty(emp.getUsername()) || StrUtil.isEmpty(emp.getPassword())) {
            throw new RuntimeException("用户名或密码不能为空");
        }

        Emp loginEmp = empMapper.findByNameAndPwd(emp);
        if (loginEmp != null) {
            log.info("员工登录成功: {}", loginEmp.getUsername());
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginEmp.getId());
            claims.put("username", loginEmp.getUsername());
            return JwtUtils.generateJwt(claims);
        } else {
            log.warn("员工登录失败: {}", emp.getUsername());
            throw new RuntimeException("用户名或密码错误");
        }
    }
}
