package org.bill.foodsystem.dao.impl;

import org.bill.foodsystem.dao.FoodDao;
import org.bill.foodsystem.entity.Food;
import org.bill.foodsystem.util.db.BaseDao;
import org.bill.foodsystem.entity.Ftype;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
    @Override
    public List<Food> selectAll() {
        String sql = "select * from food";
        List<Food> list = new ArrayList<Food>();
        BaseDao baseDao = BaseDao.getInstance();
        baseDao.open();
        ResultSet resultSet = baseDao.executeQuery(sql);

        try {
            while (resultSet.next()) {
                list.add(new Food(resultSet.getInt("fid"),
                        resultSet.getInt("tid"),
                        resultSet.getString("fname"),
                        resultSet.getString("fpic"),
                        resultSet.getDouble("fprice"),
                        resultSet.getInt("forder"),
                        resultSet.getString("fdesc"),
                        resultSet.getString("fregtime")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // baseDao.close();
        return list;
    }

    @Override
    public List<Food> selectByTid(int tid) {
        String sql = "select * from food where tid=?";
        List<Food> list = new ArrayList<Food>();
        BaseDao baseDao = BaseDao.getInstance();
        baseDao.open();
        ResultSet resultSet = baseDao.executeQuery(sql, tid);

        try {
            while (resultSet.next()) {
                list.add(new Food(resultSet.getInt("fid"),
                        resultSet.getInt("tid"),
                        resultSet.getString("fname"),
                        resultSet.getString("fpic"),
                        resultSet.getDouble("fprice"),
                        resultSet.getInt("forder"),
                        resultSet.getString("fdesc"),
                        resultSet.getString("fregtime")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // baseDao.close();
        return list;
    }

    @Override
    public Food selectByFid(int fid) {
        String sql = "select * from food where fid=?";
        Food food = null;
        BaseDao baseDao = BaseDao.getInstance();
        baseDao.open();
        ResultSet resultSet = baseDao.executeQuery(sql, fid);

        try {
            if (resultSet.next()) {

                food = new Food(resultSet.getInt("fid"),
                        resultSet.getInt("tid"),
                        resultSet.getString("fname"),
                        resultSet.getString("fpic"),
                        resultSet.getDouble("fprice"),
                        resultSet.getInt("forder"),
                        resultSet.getString("fdesc"),
                        resultSet.getString("fregtime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // baseDao.close();
        return food;
    }

    @Override
    public int insert(Food food) {
        String sql = "insert into food(tid,fname,fprice,fdesc,fregtime) values(?,?,?,?,NOW())";
        int count = -1;
        BaseDao baseDao = BaseDao.getInstance();
        baseDao.open();
        System.out.println("tid: " + food.getTid());
        count = baseDao.executeUpdate(sql, food.getTid(), food.getFname(), food.getFprice(), food.getFdesc());
        // baseDao.close();
        return count;
    }

    @Override
    public int delete(int fid) {
        String sql = "delete from food where fid=?";
        int count = -1;
        BaseDao baseDao = BaseDao.getInstance();
        baseDao.open();
        count = baseDao.executeUpdate(sql, fid);
        // baseDao.close();
        return count;
    }

    @Override
    public int update(Food food) {
        StringBuilder sql = new StringBuilder("update food set ");
        Integer tid = food.getTid();
        String fname = food.getFname();
        String fpic = food.getFpic();
        Double fprice = food.getFprice();
        Integer fid = food.getFid();
        String fdesc = food.getFdesc();
        List<Object> param = new ArrayList<Object>();
        System.out.println("tid: " + tid + " fname: " + fname + " fpic: " + fpic + " fprice: " + fprice + " fdesc: " + fdesc);
        if (tid != null) {
            sql.append("tid=?,");
            param.add(tid);
        }
        if (fname != null) {
            sql.append("fname=?,");
            param.add(fname);
        }
        if (fpic != null) {
            sql.append("fpic=?,");
            param.add(fpic);
        }
        if (fprice != null) {
            sql.append("fprice=?,");
            param.add(fprice);
        }
        if (fdesc != null) {
            sql.append("fdesc=?,");
            param.add(fdesc);
        }
        if (fid != null) {
            param.add(fid);
        } else {
            return -1;
        }

        // System.out.println(param);
        String sqlStr = sql.toString();
        String tSql = sql.substring(0, sql.length() - 1) + " where fid=?";
        // System.out.println(tSql);
        int count = -1;
        BaseDao baseDao = BaseDao.getInstance();
        baseDao.open();
        count = baseDao.executeUpdate(tSql, param.toArray());
        baseDao.close();
        return count;
    }

    @Override
    public List<Food> selectByKeywords(String keywords, Object... params) {
        if (keywords == null || keywords.equals("")){
            return null;
        } else {
            if (params == null || params.length == 0) {
                params = new Object[]{"fid", "fname", "fprice", "fdesc", "fregtime", "tname"};
            }
            StringBuilder sql = new StringBuilder(
                    "SELECT * FROM food NATURAL JOIN ftype WHERE concat(");
            /// SELECT * FROM food NATURAL JOIN ftype WHERE concat(fid, fname, fprice, fdesc, fregtime, tname) LIKE ?

            for (int i = 0; i < params.length; i++) {
                sql.append(params[i]);
                if (i != params.length - 1) {
                    sql.append(",");
                }
            }
            sql.append(") LIKE ?");
            System.out.println(sql);

            int count = -1;
            BaseDao baseDao = BaseDao.getInstance();
            baseDao.open();
            ResultSet resultSet = baseDao.executeQuery(sql.toString(), "%" + keywords + "%");
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
            //baseDao.close();
            return list;
        }
    }
}
