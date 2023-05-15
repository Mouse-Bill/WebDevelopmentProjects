package org.bill.foodsystem.servlet.food;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.HashMap;
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

@WebServlet("/food/add")
public class AddServlet extends HttpServlet {
    private FoodService foodService = new FoodServiceImpl();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        Map<String, Object> map = new HashMap<String,Object>();
        String json = br.readLine();
        System.out.println(json);
        Food food = gson.fromJson(json, Food.class);
        map.put("isOK", foodService.add(food));
        response.getWriter().write(new Gson().toJson(map));
        System.out.println("add");
    }
}
