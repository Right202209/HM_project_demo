package org.qqhru.hmpt.config;

import org.qqhru.hmpt.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 此方法就是为了给 spring boot中添加拦截器的配置
     *  InterceptorRegistry registry 参数就是 拦截器注册器
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将我们自定义的拦截器 交给spring boot处理
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns("/login"); // 排除登录路径
    }
}
