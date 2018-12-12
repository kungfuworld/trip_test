package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyContentService {
    void save(StrategyContent strategyContent);

    StrategyContent get(Long id);

    void update(StrategyContent strategyContent);

    List<StrategyContent> listAll();
}
