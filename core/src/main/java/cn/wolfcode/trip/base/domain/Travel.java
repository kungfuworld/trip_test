package cn.wolfcode.trip.base.domain;

import cn.wolfcode.trip.base.util.JsonResult;
import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Travel extends BaseDomain{
    //草稿
    public static final int STATE_NOMAL = 0;
    //待审核
    public static final int STATE_AUDIT = 1;
    //已发布
    public static final int STATE_RELEASE = 2;
    //拒绝
    public static final int STATE_REJECT = -1;

    //游记标题
    private String title;
    //旅游时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date travelTime;
    //人均消费
    private String perExpends;
    //旅游天数
    private Integer days;
    //和谁旅游(几个人)
    private Integer person;
    //作者
    private User author;
    //创建时间
    private Date createTime;
    //发布时间
    private Date releaseTime;
    //是否公开
    private Boolean isPublic;
    //旅游地区
    private Region place;
    //封面
    private String coverUrl;
    //最后更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastUpdateTime;
    //状态
    private Integer state;
    //文章文本对象
    private TravelContent travelContent;

    public Boolean getIsPublic(){
        if(isPublic!=null && isPublic){
            return true;
        }else{
            return false;
        }
    }

    public String getStateName(){
        String stateName = null;
        switch (state){
            case STATE_NOMAL :
                stateName = "草稿";
                break;
            case STATE_AUDIT :
                stateName = "待审核";
                break;
            case STATE_RELEASE :
                stateName = "已发布";
                break;
            case STATE_REJECT :
                stateName = "拒绝";
                break;
        }
        return stateName;
    }

    public String getPersonType(){
        String personType = null;
        switch(person){
            case 1:
                personType = "一个人的旅行";
                break;
            case 2:
                personType = "和父母";
                break;
            case 3:
                personType = "和朋友";
                break;
            case 4:
                personType = "和同事";
                break;
            case 5:
                personType = "和爱人";
                break;
            case 6:
                personType = "和其他";
                break;
        }
        return personType;
    }

    public String getCommendJson(){
        Map<String,Object> map = new HashMap<>();
//        map.put("data",this);?不能这样
        map.put("travelId",id);
        map.put("title",title);
        map.put("coverUrl",coverUrl);
        return JSONUtils.toJSONString(map);
    }

}