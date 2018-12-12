package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.query.UserQueryObject;
import cn.wolfcode.trip.base.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    //查询所有的用户操作
    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") UserQueryObject qo, Model m){
        m.addAttribute("pageInfo",userService.query(qo));
        return "user/list";
    }
}
