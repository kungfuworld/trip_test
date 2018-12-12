package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.mapper.StrategyContentMapper;
import cn.wolfcode.trip.base.mapper.StrategyDetailMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyDetailServiceImpl implements IStrategyDetailService {

    @Autowired
    private StrategyDetailMapper strategyDetailMapper;

    @Autowired
    private StrategyContentMapper strategyContentMapper;
    @Override
    public void save(StrategyDetail strategyDetail) {
        strategyDetail.setState(StrategyDetail.STATE_NOMAL);
        strategyDetailMapper.insert(strategyDetail);
        StrategyContent strategyContent = strategyDetail.getStrategyContent();
        strategyContent.setId(strategyDetail.getId());
        strategyContentMapper.insert(strategyContent);
    }

    @Override
    public StrategyDetail get(Long id) {
        return strategyDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(StrategyDetail strategyDetail) {
        strategyDetailMapper.updateByPrimaryKey(strategyDetail);
        StrategyContent strategyContent = strategyDetail.getStrategyContent();
        strategyContent.setId(strategyDetail.getId());
        strategyContentMapper.updateByPrimaryKey(strategyContent);
    }

    @Override
    public void updateState(Integer state,Long id) {
//        strategyDetailMapper.updateState(state,id);
    }

    @Override
    public List<StrategyDetail> listAll() {
        return strategyDetailMapper.selectAll();
    }

    @Override
    public PageInfo<StrategyDetail> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrder());
        List<StrategyDetail> list = strategyDetailMapper.queryForList(qo);
        return new PageInfo<>(list);
    }

    @Override
    public Integer getMaxSequence(Long catalogId) {
        return strategyDetailMapper.selectMaxSequence(catalogId);
    }

   /* @Override
    public List<StrategyCatalog> listByStrategyId(Long strategyId) {
        return strategyDetailMapper.selectByStrategyId(strategyId);
    }*/

   /* @Override
    public List<StrategyDetail> listByStrategyId(Long strategyId) {
        return strategyDetailMapper.selectByStrategyId(strategyId);
    }*/
}
