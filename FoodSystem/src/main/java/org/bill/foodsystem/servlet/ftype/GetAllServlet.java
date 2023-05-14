package org.bill.foodsystem.servlet.ftype;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bill.foodsystem.entity.Food;
import org.bill.foodsystem.service.FoodService;
import org.bill.foodsystem.service.FtypeService;
import org.bill.foodsystem.service.impl.FoodServiceImpl;
import org.bill.foodsystem.service.impl.FtypeServiceImpl;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Ftype/All")
public class GetAllServlet extends HttpServlet {
	
	private FtypeService ftypeService = new FtypeServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("list",ftypeService.getAll());
		
		response.getWriter().write(new Gson().toJson(map));
		
	}

}