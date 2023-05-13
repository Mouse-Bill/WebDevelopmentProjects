package org.bill.foodsystem.dao;

import org.bill.foodsystem.entity.Userinfo;

public interface UserinfoDao {
    Userinfo selectByUsernameAndPasswd(Userinfo user);
}
