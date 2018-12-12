package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.query.StrategyDetailQueryObject;
import cn.wolfcode.trip.base.service.IStrategyDetailService;
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

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/strategyDetail")
public class StrategyDetailController {

    @Autowired
    private IStrategyDetailService strategyDetailService;

    @Autowired
    private IStrategyService strategyService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") StrategyDetailQueryObject qo, Model m){
        qo.setOrder("releaseTime DESC");
        m.addAttribute("pageInfo",strategyDetailService.query(qo));
        m.addAttribute("strategies",strategyService.listAll());
        return "strategyDetail/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(StrategyDetail strategyDetail, MultipartFile file){
        JsonResult result = new JsonResult();
        //将文件存储
        if(file != null){
            String coverUrl = ImageUploadUtil.uploadFile(file, ImageUploadUtil.DICTIONERY);
            strategyDetail.setCoverUrl(coverUrl);
        }
        try {
            //如果是发布状态的且原来为空的，设置发布时间
            if(strategyDetail.getState() == StrategyDetail.STATE_RELEASE){
                strategyDetail.setReleaseTime(new Date());
            }
            //如果序号为空的，设置最大值
            if(strategyDetail.getSequence() == null){
                Integer oldSequence = strategyDetailService.getMaxSequence(strategyDetail.getCatalog().getId());
                strategyDetail.setSequence(oldSequence+1);
            }
            if(strategyDetail.getId()!=null){
                strategyDetailService.update(strategyDetail);
            } else{
                strategyDetailService.save(strategyDetail);
            }
        } catch (Exception e){
            e.printStackTrace();
            result.mark(e.getMessage());
        }
        return result;
    }

 /*   @RequestMapping("/listByStrategyId")
    @ResponseBody
    public List<StrategyDetail> listByStrategyId(Long strategyId){
        return strategyDetailService.listByStrategyId(strategyId);
    }*/

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
