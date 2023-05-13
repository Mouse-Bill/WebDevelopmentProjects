package org.bill.foodsystem.dao;

import org.bill.foodsystem.entity.Food;

import java.util.List;

public interface FoodDao {
    List<Food> selectAll();
    List<Food> selectByTid(int tid);
    Food selectByFid(int fid);
    int update(Food food);
    int insert(Food food);
    int delete(int fid);
}
