package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.service.IStrategyDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/strategyDetails")
public class StrategyDetailController {

    @Autowired
    private IStrategyDetailService strategyDetailService;

    @RequestMapping("/{id}")
    public StrategyDetail get(@PathVariable Long id){
        return strategyDetailService.get(id);
    }
}
