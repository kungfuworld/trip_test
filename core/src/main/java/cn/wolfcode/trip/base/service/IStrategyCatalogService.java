package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyCatalogService {
    void save(StrategyCatalog strategyCatalog);

    StrategyCatalog get(String email, String password);

    void update(StrategyCatalog strategyCatalog);

    List<StrategyCatalog> listByParentId(Long parentId);

    void updateState(Integer state, Long id);

    List<StrategyCatalog> listAll();

    PageInfo<StrategyCatalog> query(QueryObject qo);

    Integer getMaxSequence(Long strategyId);

    List<StrategyCatalog> listByStrategyId(Long strategyId);
}
