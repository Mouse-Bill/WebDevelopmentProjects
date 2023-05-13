package org.bill.foodsystem.service.impl;

import org.bill.foodsystem.dao.FoodDao;
import org.bill.foodsystem.dao.FtypeDao;
import org.bill.foodsystem.dao.impl.FoodDaoImpl;
import org.bill.foodsystem.dao.impl.FtypeDaoImpl;
import org.bill.foodsystem.entity.Ftype;
import org.bill.foodsystem.service.FtypeService;

import java.util.List;

public class FtypeServiceImpl implements FtypeService {
    private FoodDao foodDao = new FoodDaoImpl();
    private FtypeDao ftypeDao = new FtypeDaoImpl();

    @Override
    public List<Ftype> getAllWithFood() {
        List<Ftype> list = ftypeDao.selectAll();
        for(int i=0;i<list.size();i++){
            Ftype ftype = list.get(i);
            ftype.setFoodList(foodDao.selectByTid(ftype.getTid()));
        }
        return list;
    }

    @Override
    public List<Ftype> getAll() {
        return ftypeDao.selectAll();
    }
}
