package org.qqhru.hmpt.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component //将此类注册给spring boot
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取令牌
        String token = request.getHeader("token");

        // 2. 校验令牌
        if (StrUtil.isEmpty(token)) {
            log.info("令牌为空，拦截请求");
            return returnError(response, "NOT_LOGIN");
        }

        try {
            JwtUtils.parseJWT(token);
            return true;
        } catch (Exception e) {
            log.info("令牌解析失败，拦截请求: {}", token);
            return returnError(response, "NOT_LOGIN");
        }
    }

    private boolean returnError(HttpServletResponse response, String msg) throws Exception {
        ResultVo vo = ResultVo.error(msg);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONUtil.toJsonStr(vo));
        return false;
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
