package org.bill.foodsystem.filter;

import java.io.IOException;

import io.jsonwebtoken.Claims;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bill.foodsystem.util.jwt.JWT;

public class CrossTokenFilter implements Filter {

    public CrossTokenFilter() {
        
    }
	public void destroy() {
        
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		System.out.println("Filter");
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;

		request.setCharacterEncoding("utf-8");

		response.addHeader("Access-Control-Allow-Origin","*");
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



		String requestURI = request.getRequestURI();
		System.out.println("URI: "+ requestURI);

		if ( requestURI.contains("login") || requestURI.contains("foods")||requestURI.contains("resources")) {
			System.out.println("to login");
			chain.doFilter(req, resp);
		} else {
			String token = request.getHeader("token");
			if (token != null) {
				System.out.println(token);
				try {
					Claims claims =  JWT.parseJWT(token);
					if (claims != null) {
						response.setContentType("application/json;charset=utf-8");
						chain.doFilter(req, resp);
					}else {
						response.setStatus(401);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}else {
				response.setStatus(401);
			}
		}
		
		

	}

    public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("过滤器初始化");
    }

}
