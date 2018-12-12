package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Region{

    public static final Integer STATE_NOMAL = 0;
    public static final Integer STATE_HOT = 1;

    private Long id;

    private String name;

    private Long parentId;

    private Integer state = STATE_HOT;

    public Map<String,Object> toMap(){
        Map<String,Object>map = new HashMap<>();
        map.put("text",name);
        map.put("id",id);
        map.put("lazyLoad",true);
        if(state == STATE_HOT){
            map.put("tags",new String[]{"推荐"});
        }
//        map.put("ddd",this);
        return map;
    }

    public Map<String,Object> getRegionJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("parentId",parentId);
        return map;
    }
}