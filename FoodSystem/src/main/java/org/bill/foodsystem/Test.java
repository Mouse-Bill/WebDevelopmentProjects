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
        System.out.println("Hello World!");
        Gson gson = new Gson();
        Food food = gson.fromJson("{\"fname\":\"鸡蛋\",\"fprice\":2.5,\"ftype\":{\"ftid\":1,\"ftname\":\"蛋类\"}}", Food.class);
    }
}
