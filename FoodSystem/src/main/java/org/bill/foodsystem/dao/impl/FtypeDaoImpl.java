package org.bill.foodsystem.dao.impl;

import org.bill.foodsystem.dao.FtypeDao;
import org.bill.foodsystem.entity.Ftype;
import org.bill.foodsystem.util.db.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FtypeDaoImpl implements FtypeDao {
    @Override
    public Ftype selectByTid(int tid) {
        String sql = "select * from ftype where tid=?";
        Ftype ftype= null;
        BaseDao baseDao = BaseDao.getInstance();
        baseDao.open();
        ResultSet resultSet = baseDao.executeQuery(sql,tid );

        try {
            if(resultSet.next()) {
                ftype = new Ftype(resultSet.getInt("tid"),
                        resultSet.getString("tname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //baseDao.close();
        return ftype;
    }

    @Override
    public List<Ftype> selectAll() {
        String sql = "select * from ftype";
        List<Ftype> list = new ArrayList<Ftype>();
        BaseDao baseDao = BaseDao.getInstance();
        baseDao.open();
        ResultSet resultSet = baseDao.executeQuery(sql);
        try {
            while(resultSet.next()) {

                list.add(new Ftype(resultSet.getInt("tid"),
                        resultSet.getString("tname")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //baseDao.close();
        return list;
    }
}
