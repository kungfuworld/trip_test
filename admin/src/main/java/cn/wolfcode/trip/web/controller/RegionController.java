package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private IRegionService regionService;

    /**
     * 该方法专门用来返回左边目录栏
     */
    @RequestMapping("/list")
    public String list(){
        return "region/list";
    }

    @RequestMapping("/listByParentId")
    @ResponseBody
    public List listByParentId(Long parentId,String type){
        List<Region> regions = regionService.listByParentId(parentId);
        if("tbody".equals(type)){
            return regions;
        }
        ArrayList<Map<String,Object>> list = new ArrayList();
        for (Region region : regions) {
            list.add(region.toMap());
//            System.out.println(region.toMap());
        }
        return list;
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Region region){
        JsonResult result = new JsonResult();
        try{
            if(region.getId()!=null){
                regionService.update(region);
            } else{
                regionService.save(region);
            }
        } catch (Exception e){
            e.printStackTrace();
            result.mark(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public JsonResult updateState(Integer state,Long id){
        JsonResult result = new JsonResult();
        try{
            regionService.updateState(state,id);
        } catch (Exception e){
            e.printStackTrace();
            result.mark(e.getMessage());
        }
        return result;
    }

}
