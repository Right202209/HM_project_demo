package org.qqhru.hmpt.test;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Key;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDemo {

    @org.junit.jupiter.api.Test
    public void genJwt(){
        //核心数据
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","Tom");

        String jwt = Jwts.builder()
                .setClaims(claims) //自定义内容(载荷)    设置核心数据部分  第二部分
                .signWith(SignatureAlgorithm.HS256, "itheima") //签名算法  第一部分
                .setExpiration(new Date(System.currentTimeMillis() + 60*60*24*1000)) //有效期
                .compact(); //生成加密

        System.out.println(jwt);
    }
//    eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzM0ODM1OTYwLCJ1c2VybmFtZSI6IlRvbSJ9.oMvm20H2LsD6rSYPkY0Vi2ay_ob_vURWhMQgiJV66-A

    @Test
    public void parseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")//指定签名密钥（必须保证和生成令牌时使用相同的签名密钥）
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJUb20iLCJleHAiOjE3MzQ4NjQ1MTN9.uo_PHtVum7k9KweXPPUJqrOh-Rv48I2t5zLU3Viqhlc")
                .getBody();

        System.out.println(claims);
    }
    //    @Autowired//注入对象
//    private UserMapper userMapper;//此处报错 该报错是idea的bug 不是真的报错
//    @Test
//    public void testFindAll(){
//        List<User> userList = userMapper.findAll();
//        for (User user : userList) {
//            System.out.println(user);
//        }
//    }
}
