package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.ImageUploadUtil;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/travelCommend")
public class TravelCommendController {
    @Autowired
    private ITravelCommendService travelCommendService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") TravelCommendQueryObject qo,Model m){
        m.addAttribute("pageInfo",travelCommendService.query(qo));
        return "travel/commendList";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(TravelCommend travelCommend,MultipartFile file){
        JsonResult result = new JsonResult();
        if(file != null){
            String coverUrl = ImageUploadUtil.uploadFile(file, ImageUploadUtil.DICTIONERY);
            travelCommend.setCoverUrl(coverUrl);
        }
        try {
            if(travelCommend.getId()!=null){
                travelCommendService.update(travelCommend);
            } else{
                travelCommendService.save(travelCommend);
            }
        } catch (Exception e){
            e.printStackTrace();
            result.mark(e.getMessage());
        }
        return result;
    }
}
