package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StrategyComment extends BaseDomain {

    private User user;

    private Date createTime;

    private String content;

    private String imgUrls;

    private Integer starNum;

    private Strategy strategy;

    private Integer state;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date commendTime;

    public String[] getImgArr(){
        String[] split = imgUrls.split(";");
        return split;
    }
}