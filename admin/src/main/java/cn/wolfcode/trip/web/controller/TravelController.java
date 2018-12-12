package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/travel")
public class TravelController {
    @Autowired
    private ITravelService travelService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo")TravelQueryObject qo, Model m){
        qo.setPageSize(3);
        qo.setState(Travel.STATE_RELEASE);
        qo.setIsPublic(true);
        m.addAttribute("pageInfo",travelService.query(qo));
        System.out.println(travelService.query(qo).getList().get(0).getCommendJson());
        return "travel/list";
    }

    @RequestMapping("/audit_list")
    public String auditList(@ModelAttribute("qo")TravelQueryObject qo, Model m){
        //设置查询条件为待审核，公开的，最后更新时间升序
        if(qo.getState() == null){
            qo.setState(Travel.STATE_AUDIT);
        }
        qo.setIsPublic(true);
        qo.setOrder("lastUpdateTime ASC");
        m.addAttribute("pageInfo",travelService.query(qo));
        return "travel/audit_list";
    }

    @RequestMapping("/getTravelContent")
    @ResponseBody
    public TravelContent getTravelContentById(Long id){
        return travelService.getTravelContentById(id);
    }

    @RequestMapping("/changeState")
    @ResponseBody
    public JsonResult changeState(Travel travel){
        JsonResult result = new JsonResult();
        try{
            travelService.changeState(travel);
        } catch(Exception e){
            e.printStackTrace();
            result.mark(e.getMessage());
        }
        return result;
    }
 /*   @PostMapping
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

    @GetMapping("/{id}")
    public Travel get(@PathVariable Long id) {
        return  travelService.get(id);
    }*/

}
