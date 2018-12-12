package cn.wolfcode.trip.web.controller;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.IStrategyService;
import com.github.pagehelper.PageInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/regions")
public class RegionController {

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private ServletContext servletContext;
    /**
     * 该方法专门用来返回左边目录栏
     */
    @GetMapping
    public List<Region> list(Integer state){
        return regionService.listAll(state);
    }

    /**
     * 此方法通过通过地区id返回该地区的推荐页面数据
     * @param qo 地区id
     * @return JSON格式的攻略数据
     */
    @RequestMapping(value = "/{placeId}/strategies",produces = "text/html;charset=utf-8")
    public void queryStragiesByRegionId(StrategyQueryObject qo, HttpServletResponse response) throws Exception {
        //先把全部攻略查询出来
        PageInfo<Strategy> all = strategyService.queryByPlaceId(qo);

        //再把推荐攻略查询出来
        qo.setState(1);
        PageInfo<Strategy> hot = strategyService.queryByPlaceId(qo);

        //将数据封装到集合中
        Map<String,Object> map = new HashMap<>();
        map.put("all",all.getList());
        map.put("hot",hot.getList());
        map.put("pages",all.getPages());
        System.out.println("-----------"+all.getPages());
        //将数据给到模板中
        /*
        1.拿到配置对象
        2.设置模板所在目录
        3.设置编码
        4.获取模板
        5.将模板和数据合并
         */
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDirectoryForTemplateLoading(new File(servletContext.getRealPath("/template")));
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("strategyTemplate.ftl");
        response.setContentType("text/html;charset=utf-8");
        template.process(map,response.getWriter());
    }

    /**
     * 此方法通过通过地区id返回所有推荐攻略的JSON数据
     * @param qo 地区id
     * @return JSON格式的攻略数据
     */
    @RequestMapping(value = "/{placeId}/strategies",produces = "application/json;charset=utf-8")
    public PageInfo<Strategy> queryAllStragiesByRegionId(StrategyQueryObject qo){//测试qo是否会被转为JSON上传
        return strategyService.queryByPlaceId(qo);
    }
}
