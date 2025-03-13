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
 * 业务逻辑层
 */
@Service //表明身份
@Slf4j //日志 有这个注解, 一会执行代码的时候 控制台会输出一定的日志信息
public class EmpServiceImpl implements EmpService {

    @Autowired //注入并使用对象
    private EmpMapper empMapper;

    /**
     * 员工分页的代码
     * @param empDto
     */
    @Override
    public PageResult findByPage(EmpDto empDto) {
        //1.pageHelper 静态代码调用 参数1: 页码 参数2: 每页展示个数
        PageHelper.startPage(empDto.getPage(), empDto.getPageSize());
        //2.查询
        List<Emp> empList =  empMapper.findByPage(empDto);

        //3.分页插件 自动查询总记录数 但是需要强转empList对象
        //.分页插件 提供一个page对象 此配置对象中包含分页的所有信息
        try (Page page = (Page) empList) {


            return new PageResult(page.getTotal(), empList);
        }
    }

    /**
     * 员工保存
     * @param emp
     */
    @Override
    public void save(Emp emp) {
        //补全数据
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //保存
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

    /**
     *
     * @param emp
     */
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }
    /**
     * 登录
     * 糊涂工具包 封装了开发中很多常用的工具类
     */
    @Override
    public String login(Emp emp) {
        //1.判断用户必须输入了用户名和密码
        if(StrUtil.isEmpty(  emp.getUsername() )){
            throw  new RuntimeException("用户名不能为空");
        }
        if(StrUtil.isEmpty(  emp.getPassword() )){
            throw  new RuntimeException("密码不能为空");
        }
        //2.数据库查询登录
        Emp loginEmp = empMapper.findByNameAndPwd( emp );
        if(loginEmp != null){ //登录成功
            System.out.println("登录成功");
            //需要将成功的用户 加密后传给浏览器
            Map map = new HashMap<>();
            map.put("id" , loginEmp.getId());
            map.put("username", loginEmp.getUsername());
            String token = JwtUtils.generateJwt(map);
            return token;
        }else{ //用户名或者密码输入错误
            System.out.println("登录失败");
        }
        return "/dashboard";
    }
}

