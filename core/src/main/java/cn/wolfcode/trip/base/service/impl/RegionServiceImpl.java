package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.mapper.RegionMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public void save(Region region) {
        region.setState(Region.STATE_NOMAL);
        regionMapper.insert(region);
    }

    @Override
    public Region get(String email, String password) {
        return null;
    }

    @Override
    public void update(Region region) {
        regionMapper.updateByPrimaryKey(region);
    }

    @Override
    public List<Region> listByParentId(Long parentId) {
        return regionMapper.selectByParentId(parentId);
    }

    @Override
    public void updateState(Integer state,Long id) {
        regionMapper.updateState(state,id);
    }

    @Override
    public List<Region> listAll(Integer state) {
        return regionMapper.selectAll(state);
    }
}
