package org.bill.foodsystem.servlet.food;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    private JsonParser jsonParser = new JsonParser();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = br.readLine();
        System.out.println(json);

        JsonElement element = jsonParser.parse(json);
        JsonObject rootObject = element.getAsJsonObject();
        String query = rootObject.get("query").getAsString();
        ArrayList<String> domains = new ArrayList<String>();
        JsonObject domainObject = rootObject.get("domain").getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : domainObject.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            if (entry.getValue().getAsBoolean()) {
                domains.add(entry.getKey());
            }
        }


        List<Food> list = foodService.search(query, domains.toArray());

        request.setAttribute("list", list);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("list", list);

        response.getWriter().write(new Gson().toJson(map));
    }

}
