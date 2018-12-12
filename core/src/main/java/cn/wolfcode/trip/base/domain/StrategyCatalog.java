package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties("handler")
public class StrategyCatalog extends BaseDomain {

    private String name;

    private Strategy strategy;

    private Integer sequence;

    private Boolean state;

    public String getStrategyCatalogJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        if(strategy!=null){
            map.put("strategyId",strategy.getId());
        }
        map.put("sequence",sequence);
        map.put("state",state);
        return JSONUtils.toJSONString(map);
    }
    private List<StrategyDetail> detailList = new ArrayList<>();
}