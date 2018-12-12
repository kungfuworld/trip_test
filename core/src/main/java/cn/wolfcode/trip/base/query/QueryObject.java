package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class QueryObject {
    private int currentPage=1;
    private int pageSize=5;
    private String order;
    public String getOrder(){
        return StringUtils.hasLength(order)?order:null;
    }
}
