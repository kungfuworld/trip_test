package cn.wolfcode.trip.base.domain;

import cn.wolfcode.trip.base.util.JsonUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class StrategyDetail extends BaseDomain {

    //草稿
    public static final int STATE_NOMAL = 0;
    //发布
    public static final int STATE_RELEASE = 1;
    //禁用
    public static final int STATE_DISABLE = -1;

    private String title;

    private Date createTime;

    private Date releaseTime;

    private Integer sequence;

    private StrategyCatalog catalog;

    private String coverUrl;

    private Integer state;

    private StrategyContent strategyContent;

    public String getStateName(){
        String stateName = null;
        switch (state){
            case STATE_NOMAL :
                stateName = "草稿";
                break;
            case STATE_RELEASE :
                stateName = "发布";
                break;
            case STATE_DISABLE :
                stateName = "禁用";
                break;
        }
        return stateName;
    }

    public String getStrategyDetailJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("title",title);
        map.put("coverUrl",coverUrl);
        map.put("sequence",sequence);
        map.put("catalog",catalog);
        map.put("state",state);
        return JsonUtil.toJSON(map);
    }

}