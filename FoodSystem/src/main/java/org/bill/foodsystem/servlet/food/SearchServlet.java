package org.bill.foodsystem.servlet.food;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.annotation.WebServlet;
import org.bill.foodsystem.entity.Food;
import org.bill.foodsystem.service.FoodService;
import org.bill.foodsystem.service.impl.FoodServiceImpl;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/food/search")
public class SearchServlet extends HttpServlet {

    private FoodService foodService = new FoodServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        List<Food> list = foodService.search(request.getParameter("query"));

        request.setAttribute("list", list);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("list", list);

        response.getWriter().write(new Gson().toJson(map));
    }

}
