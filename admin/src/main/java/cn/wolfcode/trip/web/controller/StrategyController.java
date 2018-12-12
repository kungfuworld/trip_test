package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.IStrategyService;
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
@RequestMapping("/strategy")
public class StrategyController {
    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private IRegionService regionService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") StrategyQueryObject qo,Model m){
        m.addAttribute("pageInfo",strategyService.query(qo));
        m.addAttribute("regions",regionService.listAll(null));
        return "strategy/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Strategy strategy,MultipartFile file){
        JsonResult result = new JsonResult();
        if(file != null){
            String coverUrl = ImageUploadUtil.uploadFile(file, ImageUploadUtil.DICTIONERY);
            strategy.setCoverUrl(coverUrl);
        }
        try {
            if(strategy.getId()!=null){
                strategyService.update(strategy);
            } else{
                strategyService.save(strategy);
            }
        } catch (Exception e){
            e.printStackTrace();
            result.mark(e.getMessage());
        }
        return result;
    }

    /*@RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Strategy strategy,MultipartFile file){
        JsonResult result = new JsonResult();
        if(file != null){
            String coverUrl = ImageUploadUtil.uploadFile(file, ImageUploadUtil.DICTIONERY);
            strategy.setCoverUrl(coverUrl);
        }
        try {
            if(strategy.getId()!=null){
                strategyService.update(strategy);
            } else{
                strategyService.save(strategy);
            }
        } catch (Exception e){
            e.printStackTrace();
            result.mark(e.getMessage());
        }
        return result;
    }*/
}
