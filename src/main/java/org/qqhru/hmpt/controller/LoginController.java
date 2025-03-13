package org.qqhru.hmpt.controller;

import org.qqhru.hmpt.pojo.Emp;
import org.qqhru.hmpt.service.EmpService;
import org.qqhru.hmpt.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @Autowired
    private EmpService empService;
    /**
     * 登录方法
     * username password
     * @return
     */
    @PostMapping("/login")
    public ResultVo login(@RequestBody Emp emp){
        String token = empService.login(emp);
        //返回数据
        return ResultVo.success( token );
    }
}
