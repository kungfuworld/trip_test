package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ITravelService travelService;

    @GetMapping
    public JsonResult emailRepeat(User user) {
        JsonResult result = new JsonResult();
        User u = userService.getByEmailAndPassword(user.getEmail(), user.getPassword());
        if (u != null) {
            result.mark("亲，该账号已被注册");
        }
        return result;
    }

    @PostMapping
    public JsonResult register(User user) {
        JsonResult result = new JsonResult();
        try {
            userService.register(user);
        } catch (Exception e) {
            result.mark(e.getMessage());
        }
        return result;
    }

    @PutMapping("/{id}")
    public JsonResult update(User user) {
        JsonResult result = new JsonResult();
        try {
            userService.update(user);
            result.setResult(user);
        } catch (Exception e) {
            result.mark(e.getMessage());
        }
        return result;
    }

    @GetMapping("/{authorId}/travels")
    @ResponseBody
    public PageInfo<Travel> queryTravel(TravelQueryObject qo){
        qo.setPageSize(3);
        qo.setOrder("lastUpdateTime DESC");
        return travelService.query(qo);
    }
}
