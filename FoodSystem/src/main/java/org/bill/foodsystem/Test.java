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
        Food food = gson.fromJson("{\"fname\":\"ttttttt\",\"ftype\":{\"tid\":14,\"tname\":\"冰饮\"},\"fdesc\":\"tttttt\"}", Food.class);
        System.out.println(food.getTid());
    }
}
