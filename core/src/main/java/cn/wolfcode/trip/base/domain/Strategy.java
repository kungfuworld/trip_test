package cn.wolfcode.trip.base.domain;

import cn.wolfcode.trip.base.util.JsonUtil;
import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Strategy extends BaseDomain {
    //普通
    public static final int STATE_NOMAL = 0;
    //热门
    public static final int STATE_HOT = 1;
    //禁用
    public static final int STATE_DISABLE = -1;

    private Region place;

    private String title;

    private String subTitle;

    private String coverUrl;

    private Integer state = STATE_NOMAL;

    public String getStateName(){
        String stateName = null;
        switch (state){
            case STATE_NOMAL :
                stateName = "普通";
                break;
            case STATE_HOT :
                stateName = "热门";
                break;
            case STATE_DISABLE :
                stateName = "禁用";
                break;
        }
        return stateName;
    }

    public String getStrategyJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("place",place);
        map.put("title",title);
        map.put("subTitle",subTitle);
        map.put("coverUrl",coverUrl);
        map.put("state",state);
        return JsonUtil.toJSON(map);
    }
}