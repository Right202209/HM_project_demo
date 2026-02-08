package org.qqhru.hmpt.controller;

import lombok.extern.slf4j.Slf4j;
import org.qqhru.hmpt.pojo.Emp;
import org.qqhru.hmpt.service.EmpService;
import org.qqhru.hmpt.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    /**
     * 登录方法
     */
    @PostMapping("/login")
    public ResultVo login(@RequestBody Emp emp) {
        log.info("员工登录: {}", emp.getUsername());
        String token = empService.login(emp);
        return ResultVo.success(token);
    }
}
