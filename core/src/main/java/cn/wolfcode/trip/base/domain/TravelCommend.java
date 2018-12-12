package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class TravelCommend extends BaseDomain{
    //游记id
    private Travel travel;
    //标题
    private String title;
    //副标题
    private String subTitle;
    //封面
    private String coverUrl;
    //推荐时间安排
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date schedule;
    //推荐类型
    private Integer type;

    public String getTypeName(){
        String typeName = null;
        switch(type){
            case 1:
                typeName = "每月推荐";
                break;
            case 2:
                typeName = "每周推荐";
                break;
            case 3:
                typeName = "攻略推荐";
                break;
        }
        return typeName;
    }

    public String getTravelCommend(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("title",title);
        map.put("subTitle",subTitle);
        map.put("coverUrl",coverUrl);
        map.put("schedule",schedule);
        map.put("type",type);
        return JSONUtils.toJSONString(map);
    }
}