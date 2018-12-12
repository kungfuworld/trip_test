package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.domain.*;
import cn.wolfcode.trip.base.mapper.TagMapper;
import cn.wolfcode.trip.base.query.StrategyCommentQueryObject;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.*;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/strategies")
public class StrategyController {
    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private IStrategyCatalogService strategyCatalogService;

    @Autowired
    private ITravelCommendService travelCommendService;

    @Autowired
    private IStrategyCommentService strategyCommentService;

    @Autowired
    private ITagService tagService;

    @Autowired
    private TagMapper tagMapper;

    @GetMapping
    public PageInfo<Strategy> getTravels(TravelQueryObject qo) {
        return strategyService.query(qo);
    }

    @GetMapping("/commends")
    public PageInfo<TravelCommend> getTravelsForApp(TravelCommendQueryObject qo){
        return travelCommendService.queryForApp(qo);
    }

    @GetMapping("/{strategyId}/catalogs")
    public List<StrategyCatalog> getCatalogsByStrategyId(@PathVariable Long strategyId){
        return strategyCatalogService.listByStrategyId(strategyId);
    }

    @GetMapping("/{id}")
    public Strategy geStrategyById(@PathVariable Long id){
        return strategyService.get(id);
    }

    @GetMapping("/{strategyId}/comments")
    public PageInfo<StrategyComment> query(StrategyCommentQueryObject qo){
        qo.setOrder("commendTime DESC");
        return strategyCommentService.queryForList(qo);
    }

    @PostMapping("/{strategy.id}/comments")
    public JsonResult save(StrategyComment strategyComment,String[] tags){
        JsonResult result = new JsonResult();
        try{
            strategyComment.setCommendTime(new Date());
            strategyComment.setCreateTime(new Date());
            strategyComment.setUser(UserContext.getCurrentUser());
            strategyCommentService.save(strategyComment);
            for (String tag : tags){
                if(tag!=null && tag.trim().length()>0){
                    Tag t = new Tag();
                    t.setName(tag);
                    tagService.save(t);
                    tagMapper.updateCommentTagRelation(strategyComment.getId(),t.getId());
                }
            }

        } catch (Exception e){
            result.mark(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/{strategyId}/tags")
    public List<String> getTagsByStrategyId(@PathVariable Long strategyId){
        return tagService.getTagsByStrategyId(strategyId);
    }
}
