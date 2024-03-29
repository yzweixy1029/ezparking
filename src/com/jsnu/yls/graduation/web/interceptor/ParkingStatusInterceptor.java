package com.jsnu.yls.graduation.web.interceptor;

import com.jsnu.yls.graduation.service.Impl.ParkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 查询停车位状态拦截器
 *
 * Created by WeiXY on 2015/10/14.
 */
public class ParkingStatusInterceptor implements HandlerInterceptor {

    @Autowired
    private ParkingServiceImpl parkingService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        httpServletRequest.getSession().setAttribute("state_num",parkingService.checkStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
