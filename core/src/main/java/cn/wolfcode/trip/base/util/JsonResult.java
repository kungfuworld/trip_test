package cn.wolfcode.trip.base.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonResult {
    private boolean success = true;
    private String msg;
    private Object result;

    public void mark(String msg){
        this.msg = msg;
        success = false;
    }
}
