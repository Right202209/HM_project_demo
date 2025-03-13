package org.qqhru.hmpt.filter;

import cn.hutool.core.util.StrUtil;

import cn.hutool.json.JSONUtil;
import org.qqhru.hmpt.utils.JwtUtils;
import org.qqhru.hmpt.vo.ResultVo;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  urlPatterns = "/*"  表示当前过滤器 拦截所有的请求
 */
//@WebFilter(filterName = "DemoFilter" , urlPatterns = "/*")
//@Component//组件 表名身份
public class DemoFilter implements Filter {
    public void destroy() {
        System.out.println("销毁方法");
    }

    /**
     * 执行过滤的方法
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("---------------------------------------------------------");
        //强转协议对象  强转后的对象 有一些特殊的api 可以供我们使用
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //特殊情况 登录的请求不能拦截
        String url = request.getRequestURL().toString();
        if(url.contains("/login")){
            chain.doFilter(req, resp);//直接放行  如果是登录请求 不可以拦截
            return;
        }



        //1.先获得令牌
        String token = request.getHeader("token");
        //2.校验令牌 令牌为空
        if(StrUtil.isEmpty( token )){//没有登录
            //response.setStatus(401);
            ResultVo vo = ResultVo.error("NOT_LOGIN"); //通知浏览器没有登录


            //通知浏览器 我们当前放回的数据是json
            response.setContentType("application/json;charset=utf-8");

            //需要将对象转换成json 并返回
            String json = JSONUtil.toJsonStr(vo);
            //String json = JSONObject.toJSONString(vo);
            //写回给浏览器
            System.err.println("------->>"+json);
            response.getWriter().write(json);
            return ; //请求到此结束 不能放行
        }
        try {
            //3.能获得token 并且有值  需要解析token
            JwtUtils.parseJWT( token );
            //如果没有任何报错  意味着 token传递是有效的 -> 登录过的
            //放行 浏览器请求给服务器有令牌的时候 可以放行
            chain.doFilter(req, resp);
        } catch (Exception e) {
            //response.setStatus(401);
            //如果服务器报错了  意味着 token的传递是有问题的 token可能被修改过
            ResultVo vo = ResultVo.error("NOT_LOGIN"); //通知浏览器没有登录

            //通知浏览器 我们当前放回的数据是json
            response.setContentType("application/json;charset=utf-8");

            //需要将对象转换成json 并返回
            //String json = JSONObject.toJSONString(vo);
            String json = JSONUtil.toJsonStr(vo);
            //写回给浏览器
            System.err.println("========>>"+json);
            response.getWriter().write(json);
            return ; //请求到此结束 不能放行
        }



    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("初始化方法");
    }

}
