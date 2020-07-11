package com.ucpeo.activity.config;

import com.alibaba.fastjson.JSON;
import com.ucpeo.activity.bean.Resp;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            Resp resp = new Resp();
            resp.setMsg("您未登录！请登录后继续");
            resp.setCode(410);
            response.setHeader("content-type", "application/json;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(JSON.toJSONString(resp));
            response.setStatus(resp.getCode());
            return false;
        }
        return true;
    }
}
