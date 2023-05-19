package org.bill.foodsystem.service.impl;

import org.bill.foodsystem.dao.FoodDao;
import org.bill.foodsystem.dao.FtypeDao;
import org.bill.foodsystem.dao.impl.FoodDaoImpl;
import org.bill.foodsystem.dao.impl.FtypeDaoImpl;
import org.bill.foodsystem.entity.Food;
import org.bill.foodsystem.entity.Ftype;
import org.bill.foodsystem.service.FoodService;
import org.bill.foodsystem.util.db.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodServiceImpl implements FoodService {
    private FoodDao foodDao = new FoodDaoImpl();
    private FtypeDao ftypeDao = new FtypeDaoImpl();
    
    @Override
    public List<Food> getAllWithFtype() {
//        List<Food> list = foodDao.selectAll();
//        Map<Integer, Ftype> map = new HashMap<Integer, Ftype>();
//        int count =0;
//        for(int i=0;i<list.size();i++) {
//            Food food = list.get(i);
//            int tid = food.getTid();
//            Ftype ftype = map.get(tid);
//
//            if(ftype==null) {
//                ftype = ftypeDao.selectByTid(tid);
//                count++;
//                map.put(tid,ftype);
//            }
//            food.setFtype(ftype);
//        }
//        System.out.println(count);
        String sql = "select * from food natural join ftype";
        BaseDao baseDao = BaseDao.getInstance();
        baseDao.open();
        ResultSet resultSet = baseDao.executeQuery(sql);

        List<Food> list = new ArrayList<Food>();

        try {
            while (resultSet.next()) {
                Food tFood = new Food(resultSet.getInt("fid"),
                        resultSet.getInt("tid"),
                        resultSet.getString("fname"),
                        resultSet.getString("fpic"),
                        resultSet.getDouble("fprice"),
                        resultSet.getInt("forder"),
                        resultSet.getString("fdesc"),
                        resultSet.getString("fregtime"));
                tFood.setFtype(new Ftype(resultSet.getInt("tid"), resultSet.getString("tname")));
                list.add(tFood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean add(Food food) {
        int count = foodDao.insert(food);
        if(count>0)
            return true;
        return false;
    }

    @Override
    public boolean remove(int fid) {
        int count = foodDao.delete(fid);
        if(count>0)
            return true;
        return false;
    }

    @Override
    public boolean change(Food food) {
        if(food==null)
            return false;
        if(food.getFid()==null)
            return false;
        if(food.getTid()==null && food.getFname()== null && food.getFpic()==null && food.getFprice() == null )
            return false;
        int count = foodDao.update(food);
        if(count>0)
            return true;
        return false;
    }

    @Override
    public Food getByFid(int fid) {
        return foodDao.selectByFid(fid);
    }

    @Override
    public List<Food> search(String keywords, Object... params) {
        if (keywords == null || keywords.trim().equals("") || params == null || params.length == 0){
            return getAllWithFtype();
        }
        return foodDao.selectByKeywords(keywords, params);
    }
}
