package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyDetailService {
    void save(StrategyDetail strategyDetail);

    StrategyDetail get(Long id);

    void update(StrategyDetail strategyDetail);

    void updateState(Integer state, Long id);

    List<StrategyDetail> listAll();

    PageInfo<StrategyDetail> query(QueryObject qo);

    Integer getMaxSequence(Long catalog);

//    List<StrategyCatalog> listByStrategyId(Long strategyId);


//    List<StrategyDetail> listByStrategyId(Long strategyId);
}
