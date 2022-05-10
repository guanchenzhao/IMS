/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gc.ims.core.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.gc.ims.core.consts.Constant;
import com.gc.ims.model.dto.ResponseDto;
import com.gc.ims.util.RedisUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.gc.ims.model.dto.ResponseDto.KEY_MISS_OR_NOT_CORRECT;


/**
 * RestApiInterceptor
 */
@Component
public class RestApiInterceptor implements HandlerInterceptor {


    private final RedisUtil redisUtil = new RedisUtil();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return check(request, response);
    }

    private boolean check(HttpServletRequest request, HttpServletResponse response) {
        String requestHeader = request.getHeader(Constant.AUTH_HEADER);
        String authToken;
        if (requestHeader != null) {
            authToken = requestHeader;
            try {
                if (redisUtil.exists(authToken)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }

        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSONObject.toJSONString(ResponseDto.error(KEY_MISS_OR_NOT_CORRECT)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("Inside post handle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exception)
            throws Exception {
        System.out.println("Inside after completion");
    }


}
