package org.qqhru.hmpt.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.qqhru.hmpt.utils.JwtUtils;
import org.qqhru.hmpt.vo.ResultVo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 1.编写一个拦截器
 * 1.1 此类必须实现拦截器的接口
 * 1.2 实现接口中方法
 * 2.配置拦截器
 * 2.1 必须实现 implements WebMvcConfigurer  这个接口
 */
@Component //将此类注册给spring boot
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 放行之前执行的代码
     * preHandle : 此方法是有返回值的  如果返回true  表示放行  如果返回的是false 表示不放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.先获得令牌
        String token = request.getHeader("token");
//        token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzM0ODYyMzIyLCJ1c2VybmFtZSI6IlRvbSJ9.-du7HmBGu8Pv6TcKxEZg9ZTmJc41nYLjWfE7J73LUrw";
        //2.校验令牌 令牌为空
        if(StrUtil.isEmpty( token )){//没有登录
            //response.setStatus(401);
            ResultVo vo = ResultVo.error("NOT_LOGIN1"); //通知浏览器没有登录


            //通知浏览器 我们当前放回的数据是json
            response.setContentType("application/json;charset=utf-8");

            //需要将对象转换成json 并返回
            String json = JSONUtil.toJsonStr(vo);
            //String json = JSONObject.toJSONString(vo);
            //写回给浏览器
            System.err.println("------->>"+json);
            response.getWriter().write(json);
            return false; //请求到此结束 不能放行
        }
        try {
            //3.能获得token 并且有值  需要解析token
            JwtUtils.parseJWT( token );
            //如果没有任何报错  意味着 token传递是有效的 -> 登录过的
            //放行 浏览器请求给服务器有令牌的时候 可以放行
            return true;
        } catch (Exception e) {
            System.out.println(token);
            //response.setStatus(401);
            //如果服务器报错了  意味着 token的传递是有问题的 token可能被修改过
            ResultVo vo = ResultVo.error("NOT_LOGIN2"); //通知浏览器没有登录

            //通知浏览器 我们当前放回的数据是json
            response.setContentType("application/json;charset=utf-8");

            //需要将对象转换成json 并返回
            //String json = JSONObject.toJSONString(vo);
            String json = JSONUtil.toJsonStr(vo);
            //写回给浏览器
            System.err.println("========>>"+json);
            response.getWriter().write(json);
            return false; //请求到此结束 不能放行
        }
    }

    //放行之后执行的代码
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("放行之后执行的代码 ");
    }

    //程序执行完毕后 将要返回给浏览器时候执行的代码  一般情况下 此方法用不上
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
