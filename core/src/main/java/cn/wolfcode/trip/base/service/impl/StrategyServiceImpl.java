package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.mapper.StrategyMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyServiceImpl implements IStrategyService {

    @Autowired
    private StrategyMapper strategyMapper;

    @Override
    public void save(Strategy strategy) {
        strategy.setState(Strategy.STATE_NOMAL);
        strategyMapper.insert(strategy);
    }

    @Override
    public Strategy get(Long id) {
        return strategyMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Strategy strategy) {
        strategyMapper.updateByPrimaryKey(strategy);
    }

    @Override
    public PageInfo<Strategy> queryByPlaceId(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Strategy> list = strategyMapper.queryByPlaceId(qo);
        return new PageInfo<>(list);
    }

    @Override
    public void updateState(Integer state,Long id) {
//        strategyMapper.updateState(state,id);
    }

    @Override
    public List<Strategy> listAll() {
        return strategyMapper.selectAll();
    }

    @Override
    public PageInfo<Strategy> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Strategy> list = strategyMapper.queryForList(qo);
        return new PageInfo<>(list);
    }


}
