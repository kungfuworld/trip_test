package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import com.github.pagehelper.PageInfo;

public interface ITravelCommendService {
    void save(TravelCommend travelCommend);

    PageInfo<TravelCommend> query(QueryObject qo);

    void update(TravelCommend travelCommend);

    PageInfo<TravelCommend> queryForApp(TravelCommendQueryObject qo);
}
