package org.bill.foodsystem;

import com.google.gson.Gson;
import org.bill.foodsystem.entity.Food;
import org.bill.foodsystem.entity.Ftype;
import org.bill.foodsystem.entity.Userinfo;
import org.bill.foodsystem.service.FoodService;
import org.bill.foodsystem.service.FtypeService;
import org.bill.foodsystem.service.UserinfoService;
import org.bill.foodsystem.service.impl.FoodServiceImpl;
import org.bill.foodsystem.service.impl.FtypeServiceImpl;
import org.bill.foodsystem.service.impl.UserinfoServiceImpl;
import org.bill.foodsystem.util.db.BaseDao;

import java.sql.ResultSet;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        FoodService foodService = new FoodServiceImpl();
        List<Food> list = foodService.getAllWithFtype();
        Gson gson = new Gson();
        Food food = list.get(list.size() - 1);
        System.out.println(gson.toJson(food));
        food.setFprice(18.0);
        boolean flag = foodService.change(food);
        System.out.println(flag);

    }
}
