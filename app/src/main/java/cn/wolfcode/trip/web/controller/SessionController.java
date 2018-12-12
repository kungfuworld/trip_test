package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    @Autowired
    private IUserService userService;

    @PostMapping
    public JsonResult login(User user){
        JsonResult result = new JsonResult();
        try {
            User u = userService.login(user);
            result.setResult(u);
        } catch (Exception e) {
            result.mark(e.getMessage());
        }
        return result;
    }

    @DeleteMapping
    public void logout(HttpServletResponse response){
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            userService.logout();
    }
}
