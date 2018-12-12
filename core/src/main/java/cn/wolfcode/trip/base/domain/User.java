package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class User {
    private Long id;

    private String email;

    private String nickName;

    private String password;

    private String place;

    private String headImgUrl;

    private Integer gender;

    private String coverImgUrl;

    private String sign;

    public String getPassword(){
        return password!=null&&StringUtils.hasLength(password) ? password : null;
    }
}