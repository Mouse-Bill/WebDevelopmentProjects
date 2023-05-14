package org.bill.foodsystem.servlet.food;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bill.foodsystem.service.FoodService;
import org.bill.foodsystem.service.impl.FoodServiceImpl;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Food/Remove")
public class RemoveServlet extends HttpServlet {
	
	private FoodService foodService = new FoodServiceImpl();
	private Gson gson = new Gson();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("isOK",foodService.remove(Integer.parseInt(request.getParameter("tid"))));
		
		response.getWriter().write(new Gson().toJson(map));
		
	}
	
	

}

