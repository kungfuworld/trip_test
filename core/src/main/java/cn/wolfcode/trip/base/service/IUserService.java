package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.UserQueryObject;
import com.github.pagehelper.PageInfo;

public interface IUserService {
    public void save();
    User getByEmailAndPassword(String email, String password);

    void register(User user);

    User login(User user);

    void logout();

    void update(User user);

    PageInfo<User> query(QueryObject qo);
}
