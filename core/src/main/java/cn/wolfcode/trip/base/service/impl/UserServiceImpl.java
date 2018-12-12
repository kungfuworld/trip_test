package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.UserMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.UserQueryObject;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public void save() {

    }

    public User getByEmailAndPassword(String email, String password) {
        User user = userMapper.selectByEmailAndPassword(email,password);
        return user;
    }

    public void register(User user) {
        User u = userMapper.selectByEmailAndPassword(user.getEmail(),null);
        if (u != null) {
            throw new RuntimeException("亲，该账号已被注册");
        }
        user.setCoverImgUrl("/img/user/bg.jpeg");
        user.setHeadImgUrl("/img/user/head.jpg");
        userMapper.insert(user);
    }

    public User login(User user) {
        User u = userMapper.selectByEmailAndPassword(user.getEmail(), user.getPassword());
        if(u == null){
            throw new RuntimeException("亲，账号或密码错误");
        }
        //将用户存入Session中
        UserContext.setCurrentUser(u);
        return u;
    }

    public void logout() {
        UserContext.setCurrentUser(null);
    }

    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    public PageInfo<User> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<User> list = userMapper.selectForList(qo);
        return new PageInfo<User>(list);
    }
}
