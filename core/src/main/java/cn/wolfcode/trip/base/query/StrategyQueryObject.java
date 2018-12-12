package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StrategyQueryObject extends QueryObject {
    private String keywords;
    private Integer state;
    private Integer placeId;

    public String getKeywords(){
        return keywords!=null&&keywords.trim().length() > 0 ? keywords : null;
    }
}
