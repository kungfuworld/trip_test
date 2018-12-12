package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyService {
    void save(Strategy strategy);

    Strategy get(Long id);

    void update(Strategy strategy);

    PageInfo<Strategy> queryByPlaceId(QueryObject qo);

    void updateState(Integer state, Long id);

    List<Strategy> listAll();

    PageInfo<Strategy> query(QueryObject qo);

}
