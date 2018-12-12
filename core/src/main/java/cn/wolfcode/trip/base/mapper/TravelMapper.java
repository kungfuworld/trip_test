package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.query.QueryObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TravelMapper {
    int insert(Travel travel);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(Travel travel);

    Travel selectByPrimaryKey(Long id);

    List<Travel> selectAll();

    List<Travel> selectForList(QueryObject qo);

    void changeState(Travel Travel);
}