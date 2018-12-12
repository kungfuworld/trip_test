package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.mapper.TravelCommendMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelCommendServiceImpl implements ITravelCommendService {

    @Autowired
    private TravelCommendMapper travelCommendMapper;


    @Override
    public void save(TravelCommend travelCommend) {
        travelCommendMapper.insert(travelCommend);
    }

    @Override
    public PageInfo<TravelCommend> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<TravelCommend> list = travelCommendMapper.selectForList(qo);
        return new PageInfo<>(list);
    }

    @Override
    public void update(TravelCommend travelCommend) {
        travelCommendMapper.updateByPrimaryKey(travelCommend);
    }

    @Override
    public PageInfo<TravelCommend> queryForApp(TravelCommendQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<TravelCommend> list = travelCommendMapper.selectForApp(qo);
        return new PageInfo<>(list);
    }
}
