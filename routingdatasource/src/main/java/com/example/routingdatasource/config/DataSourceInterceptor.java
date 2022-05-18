package com.example.routingdatasource.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.routingdatasource.constant.BranchEnum;

@Component
public class DataSourceInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {

		String branch = request.getHeader("branch");
		if (BranchEnum.KOREA.toString().equalsIgnoreCase(branch)) {
			BranchContextHolder.setBranchContext(BranchEnum.KOREA);
		} else {
			BranchContextHolder.setBranchContext(BranchEnum.JAPAN);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		BranchContextHolder.clearBranchContext();
	}
}
