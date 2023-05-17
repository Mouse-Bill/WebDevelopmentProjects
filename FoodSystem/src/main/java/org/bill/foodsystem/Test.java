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
import org.bill.foodsystem.util.jwt.JWT;

import java.sql.ResultSet;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        String token = JWT.createJWT("hhhhhhhh","dhgfajhjh", 1000 * 60 * 60);
        System.out.println(token);
    }
}
