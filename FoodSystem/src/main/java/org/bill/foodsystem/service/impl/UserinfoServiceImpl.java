package org.bill.foodsystem.service.impl;

import org.bill.foodsystem.dao.UserinfoDao;
import org.bill.foodsystem.dao.impl.UserinfoDaoImpl;
import org.bill.foodsystem.entity.Userinfo;
import org.bill.foodsystem.service.UserinfoService;

public class UserinfoServiceImpl implements UserinfoService {
    private UserinfoDao userInfoDao = new UserinfoDaoImpl();

    @Override
    public Userinfo login(Userinfo userinfo) {
        return userInfoDao.selectByUsernameAndPasswd(userinfo);
    }

}
