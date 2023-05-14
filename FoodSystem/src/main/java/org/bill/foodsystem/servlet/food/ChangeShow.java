package org.bill.foodsystem.servlet.food;

import java.io.IOException;

import org.bill.foodsystem.entity.Food;
import org.bill.foodsystem.service.FoodService;
import org.bill.foodsystem.service.FtypeService;
import org.bill.foodsystem.service.impl.FoodServiceImpl;
import org.bill.foodsystem.service.impl.FtypeServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeShow extends HttpServlet {

    private FoodService foodService = new FoodServiceImpl();
	private FtypeService ftypeService = new FtypeServiceImpl();
		
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int fid = Integer.parseInt(request.getParameter("fid"));
		Food food  = foodService.getByFid(fid);
		
		request.setAttribute("food", food);
		request.setAttribute("typeList", ftypeService.getAll());
		
		request.getRequestDispatcher("/ShowChange.jsp").forward(request, response);;
		
	}
}
