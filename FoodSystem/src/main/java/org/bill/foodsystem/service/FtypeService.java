package org.bill.foodsystem.service;

import org.bill.foodsystem.entity.Ftype;

import java.util.List;

public interface FtypeService {
    List<Ftype> getAllWithFood();
    List<Ftype> getAll();
}
