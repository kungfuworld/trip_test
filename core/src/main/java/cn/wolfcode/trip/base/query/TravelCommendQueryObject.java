package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TravelCommendQueryObject extends QueryObject {
    private String keywords;
    private Integer type;
    private Long id;

    public String getKeywords(){
        return keywords!=null&&keywords.trim().length() > 0 ? keywords : null;
    }
}
