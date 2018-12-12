package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;

import java.util.List;

public interface TravelCommendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TravelCommend record);

    TravelCommend selectByPrimaryKey(Long id);

    List<TravelCommend> selectAll();

    int updateByPrimaryKey(TravelCommend record);

    List<TravelCommend> selectForList(QueryObject qo);

    List<TravelCommend> selectForApp(TravelCommendQueryObject qo);
}