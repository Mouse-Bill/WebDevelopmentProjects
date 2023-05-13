package org.bill.foodsystem.dao.impl;

import org.bill.foodsystem.dao.UserinfoDao;
import org.bill.foodsystem.entity.Userinfo;
import org.bill.foodsystem.util.db.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserinfoDaoImpl implements UserinfoDao {
    @Override
    public Userinfo selectByUsernameAndPasswd(Userinfo user) {
        String sql = "select * from userinfo where username=? and passwd=?";
        Userinfo getUser = null;

        BaseDao baseDao = BaseDao.getInstance();

        baseDao.open();

        ResultSet resultSet = baseDao.executeQuery(sql, user.getUsername(),user.getPasswd());

        try {
            if(resultSet.next()) {
                getUser = new Userinfo(resultSet.getInt("userid"),
                        user.getUsername(),"******",
                        resultSet.getString("nickname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //baseDao.close();

        return getUser;

    }
}
