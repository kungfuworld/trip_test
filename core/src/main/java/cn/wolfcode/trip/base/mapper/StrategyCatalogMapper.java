package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.query.QueryObject;
import java.util.List;

public interface StrategyCatalogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StrategyCatalog record);

    StrategyCatalog selectByPrimaryKey(Long id);

    List<StrategyCatalog> selectAll();

    int updateByPrimaryKey(StrategyCatalog record);

    List<StrategyCatalog> queryForList(QueryObject qo);

    Integer selectMaxSequence(Long strategyId);

    List<StrategyCatalog> selectByStrategyId(Long strategyId);
}