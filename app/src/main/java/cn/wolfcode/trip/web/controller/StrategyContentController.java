package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.query.StrategyContentQueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.IStrategyContentService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/strategyContents")
public class StrategyContentController {
    @Autowired
    private IStrategyContentService strategyContentService;

    @RequestMapping("/id")
    @ResponseBody
    public StrategyContent get(@PathVariable Long id){
        StrategyContent strategyContent = strategyContentService.get(id);
        return strategyContent;
    }
}
