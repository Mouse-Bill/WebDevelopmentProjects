package org.bill.foodsystem.service;

import org.bill.foodsystem.entity.Food;

import java.util.List;

public interface FoodService {
    List<Food> getAllWithFtype();
    Food getByFid(int fid);
    boolean add(Food food);
    boolean remove(int fid);
    boolean change(Food food);
    List<Food> search(String keywords);
}
