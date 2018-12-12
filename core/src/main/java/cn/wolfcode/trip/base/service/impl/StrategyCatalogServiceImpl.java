package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.mapper.StrategyCatalogMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyCatalogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyCatalogServiceImpl implements IStrategyCatalogService {

    @Autowired
    private StrategyCatalogMapper strategyCatalogMapper;

    @Override
    public void save(StrategyCatalog strategyCatalog) {
//        strategyCatalog.setState(StrategyCatalog.STATE_NOMAL);
        strategyCatalogMapper.insert(strategyCatalog);
    }

    @Override
    public StrategyCatalog get(String email, String password) {
        return null;
    }

    @Override
    public void update(StrategyCatalog strategyCatalog) {
        strategyCatalogMapper.updateByPrimaryKey(strategyCatalog);
    }

    @Override
    public List<StrategyCatalog> listByParentId(Long parentId) {
//        return strategyCatalogMapper.selectByParentId(parentId);
        return null;
    }

    @Override
    public void updateState(Integer state,Long id) {
//        strategyCatalogMapper.updateState(state,id);
    }

    @Override
    public List<StrategyCatalog> listAll() {
        return strategyCatalogMapper.selectAll();
    }

    @Override
    public PageInfo<StrategyCatalog> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrder());
        List<StrategyCatalog> list = strategyCatalogMapper.queryForList(qo);
        return new PageInfo<>(list);
    }

    @Override
    public Integer getMaxSequence(Long strategyId) {
        return strategyCatalogMapper.selectMaxSequence(strategyId);
    }

    @Override
    public List<StrategyCatalog> listByStrategyId(Long strategyId) {
        return strategyCatalogMapper.selectByStrategyId(strategyId);
    }
}
