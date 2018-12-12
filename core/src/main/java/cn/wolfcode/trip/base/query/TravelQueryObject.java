package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Setter
@Getter
public class TravelQueryObject extends QueryObject {
    private String keywords;
    private Long authorId;
    private Integer state;
    private Boolean isPublic;

    public String getKeywords(){
        return keywords!=null&&keywords.trim().length() > 0 ? keywords : null;
    }
}
