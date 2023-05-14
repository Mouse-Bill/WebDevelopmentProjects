package org.bill.foodsystem.filter;

import java.io.IOException;
import java.util.List;

import org.bill.foodsystem.entity.Food;
import org.bill.foodsystem.service.FoodService;
import org.bill.foodsystem.service.impl.FoodServiceImpl;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/*")
public class CrossFilter implements Filter {

    public CrossFilter() {
        
    }
	public void destroy() {
        
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("过滤器");
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin")); 
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Headers", 
				"Accept,"
				+ "Origin,"
				+ "No-Cache,"
				+ "X-Requested-With,"
				+ "If-Modified-Since,"
				+ "Pragma,"
				+ "Last-Modified,"
				+ "Cache-Control,"
				+ "Expires,"
				+ "Content-Type,"
				+ "X-E4M-With");
		
		chain.doFilter(req, res);
	}

    public void init(FilterConfig fConfig) throws ServletException {
	
    }

}
