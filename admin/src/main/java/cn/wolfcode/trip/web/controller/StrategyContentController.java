package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.query.StrategyContentQueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.IStrategyContentService;
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
@RequestMapping("/strategyContent")
public class StrategyContentController {
    @Autowired
    private IStrategyContentService strategyContentService;

    @Autowired
    private IRegionService regionService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") StrategyContentQueryObject qo,Model m){
/*        m.addAttribute("pageInfo",strategyContentService.query(qo));
        m.addAttribute("regions",regionService.listAll());*/
        return null;
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(StrategyContent strategyContent,MultipartFile file){
        JsonResult result = new JsonResult();
        /*if(file != null){
            String coverUrl = ImageUploadUtil.uploadFile(file, ImageUploadUtil.DICTIONERY);
            strategyContent.setCoverUrl(coverUrl);
        }
        try {
            if(strategyContent.getId()!=null){
                strategyContentService.update(strategyContent);
            } else{
                strategyContentService.save(strategyContent);
            }
        } catch (Exception e){
            e.printStackTrace();
            result.mark(e.getMessage());
        }*/
        return result;
    }

    @RequestMapping("/getById")
    @ResponseBody
    public StrategyContent get(Long id){
        StrategyContent strategyContent = strategyContentService.get(id);
        return strategyContent;
    }
}
