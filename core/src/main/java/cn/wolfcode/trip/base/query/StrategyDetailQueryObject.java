package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StrategyDetailQueryObject extends QueryObject {
    private String keywords;
    public String getKeywords(){
        return keywords!=null&&keywords.trim().length() > 0 ? keywords : null;
    }
}
