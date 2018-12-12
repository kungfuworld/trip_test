package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/travels")
public class TravelController {
    @Autowired
    private ITravelService travelService;

    @Autowired
    private ITravelCommendService travelCommendService;

    @PostMapping
    public JsonResult save(Travel travel) {
        JsonResult result = new JsonResult();
        try {
            travelService.save(travel);
        } catch (Exception e) {
            e.printStackTrace();
            result.mark(e.getMessage());
        }
        return result;
    }

    @GetMapping
    public PageInfo<Travel> getTravels(TravelQueryObject qo) {
        qo.setPageSize(3);
        qo.setOrder("releaseTime DESC");
        qo.setState(Travel.STATE_RELEASE);
        return travelService.query(qo);
    }

    @GetMapping("/{id}")
    public Travel get(@PathVariable Long id) {
        return  travelService.get(id);
    }

    /**
     * 使用PageInfo的原因是前台页面需要使用循环，如果传list还需要另外封装
     */
    @GetMapping("/commends")
    public PageInfo<TravelCommend> getTravelsForApp(TravelCommendQueryObject qo){
        return travelCommendService.queryForApp(qo);
    }
}
