package com.alan.springboothelloworld.common.config;

import com.alan.springboothelloworld.helloworld.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *重写方法中内容根据具体项目来
 */
public class LoginInterceptor  implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 进入controller层之前拦截请求
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        log.info("---------------------开始进入请求地址拦截----------------------------");
        String uri = request.getRequestURI();
        if(uri.contains("wechat")||uri.contains("api")||uri.contains("wxuser")){  // 允许开放的请求路径
            return true;
        }
        User user = (User)request.getSession().getAttribute("user");
        if(!uri.contains("login")){ //如果不登陆（即访问其他接口）。判断是否存在User用户，不存在，重定向到登录页
            if(user==null){
                response.sendRedirect("/user/tologin");
                return false;
            }
        }else{
            if(user!=null){ // 如果登陆成功，则重定向到首页
                response.sendRedirect("/main/index");
                return false;
            }
        }
        return true;

    }


    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }


    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("---------------视图渲染之后的操作-------------------------0");
    }

}
