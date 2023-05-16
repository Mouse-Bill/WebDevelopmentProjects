package org.bill.foodsystem.servlet.food;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bill.foodsystem.service.FoodService;
import org.bill.foodsystem.service.impl.FoodServiceImpl;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/food/delete")
public class RemoveServlet extends HttpServlet {
	private FoodService foodService = new FoodServiceImpl();
	private JsonParser jsonParser = new JsonParser();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		Map<String, Object> map = new HashMap<String,Object>();
		String json = br.readLine();
		System.out.println(json);

		JsonElement element = jsonParser.parse(json);
		JsonObject object = element.getAsJsonObject();
		int tid = object.get("fid").getAsInt();
		map.put("isOK",foodService.remove(tid));
		
		response.getWriter().write(new Gson().toJson(map));
		
	}
	
	

}

