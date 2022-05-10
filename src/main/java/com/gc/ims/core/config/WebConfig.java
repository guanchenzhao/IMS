package com.gc.ims.core.config;

import com.gc.ims.core.consts.Constant;
import com.gc.ims.core.interceptor.RestApiInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: WebConfig Interceptor
 * @Author: Guanchen Zhao
 * @Date: 2021/11/27
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 增加对rest api鉴权的spring mvc拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new RestApiInterceptor()).excludePathPatterns(Constant.NONE_PERMISSION_RES).addPathPatterns("/**");
    }
}
