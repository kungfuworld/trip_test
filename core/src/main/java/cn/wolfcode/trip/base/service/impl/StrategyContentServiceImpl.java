package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.mapper.StrategyContentMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyContentServiceImpl implements IStrategyContentService {

    @Autowired
    private StrategyContentMapper strategyContentMapper;

    @Override
    public void save(StrategyContent strategyContent) {
    }

    @Override
    public StrategyContent get(Long id) {
        return strategyContentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(StrategyContent strategyContent) {
        strategyContentMapper.updateByPrimaryKey(strategyContent);
    }



    @Override
    public List<StrategyContent> listAll() {
        return strategyContentMapper.selectAll();
    }

}
