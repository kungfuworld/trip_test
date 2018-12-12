package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ITravelService {
    void save(Travel Travel);

    void delete(Long id);

    void update(Travel Travel);

    Travel get(Long id);

    List<Travel> listAll();

    PageInfo<Travel> query(QueryObject qo);

    TravelContent getTravelContentById(Long id);

    void changeState(Travel Travel);

}
