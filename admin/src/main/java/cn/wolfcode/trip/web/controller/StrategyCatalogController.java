package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.query.StrategyCatalogQueryObject;
import cn.wolfcode.trip.base.service.IStrategyCatalogService;
import cn.wolfcode.trip.base.service.IStrategyService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/strategyCatalog")
public class StrategyCatalogController {
    @Autowired
    private IStrategyCatalogService strategyCatalogService;

    @Autowired
    private IStrategyService strategyService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") StrategyCatalogQueryObject qo, Model m) {
        qo.setOrder("sequence ASC");
        m.addAttribute("pageInfo", strategyCatalogService.query(qo));
        m.addAttribute("strategies", strategyService.listAll());
        return "strategyCatalog/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(StrategyCatalog strategyCatalog) {
        JsonResult result = new JsonResult();
        try {
            if(strategyCatalog.getSequence() == null){
                Integer oldSequence = strategyCatalogService.getMaxSequence(strategyCatalog.getStrategy().getId());
                strategyCatalog.setSequence(oldSequence+1);
            }
            if (strategyCatalog.getId() != null) {
                strategyCatalogService.update(strategyCatalog);
            } else {
                strategyCatalogService.save(strategyCatalog);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.mark(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/listByStrategyId")
    @ResponseBody
    public List<StrategyCatalog> listByStrategyId(Long strategyId){
        return strategyCatalogService.listByStrategyId(strategyId);
    }
}
