package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TravelContentMapper {
    int insert(TravelContent travelContent);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(TravelContent travelContent);

    TravelContent selectByPrimaryKey(Long id);

    List<TravelContent> selectAll();

}