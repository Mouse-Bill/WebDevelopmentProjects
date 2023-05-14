package org.bill.foodsystem.servlet.food;

import java.io.IOException;
import java.util.List;

import org.bill.foodsystem.entity.Food;
import org.bill.foodsystem.service.FoodService;
import org.bill.foodsystem.service.impl.FoodServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Food/All")
public class GetAllServlet extends HttpServlet {

    private FoodService foodService = new FoodServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Food> list = foodService.getAllWithFtype();

        request.setAttribute("list", list);

        request.getRequestDispatcher("/Show.jsp").forward(request, response);

        // Map<String,Object> map = new HashMap<String, Object>();

        // map.put("list",list);

        // response.getWriter().write(new Gson().toJson(map));

    }

}
