package org.bill.foodsystem.dao;

import org.bill.foodsystem.entity.Ftype;

import java.util.List;

public interface FtypeDao {
    Ftype selectByTid(int tid);
    List<Ftype> selectAll();
}
