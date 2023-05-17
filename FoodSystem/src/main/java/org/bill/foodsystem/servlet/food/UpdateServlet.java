package org.bill.foodsystem.servlet.food;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bill.foodsystem.entity.Food;
import org.bill.foodsystem.service.FoodService;
import org.bill.foodsystem.service.impl.FoodServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/food/update")
public class UpdateServlet extends HttpServlet {
    private Gson gson = new Gson();
    private FoodService foodService = new FoodServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("update");
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        Map<String, Object> map = new HashMap<String,Object>();
        String json = br.readLine();
        System.out.println(json);
        Food food = gson.fromJson(json, Food.class);
        //System.out.println(food.getFid()+food.getFname()+food.getFprice()+food.getFtype().getTid());
        boolean isOK = foodService.change(food);
        map.put("isOK", isOK);
        resp.getWriter().write(new Gson().toJson(map));
    }
}
