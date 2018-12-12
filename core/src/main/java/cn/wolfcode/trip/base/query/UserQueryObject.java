package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Setter
@Getter
public class UserQueryObject extends QueryObject {
    private String keywords;

    public String getKeywords(){
        return keywords!=null&&StringUtils.hasLength(keywords) ? keywords : null;
    }
}
