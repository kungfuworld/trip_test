package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IRegionService {
    void save(Region region);

    Region get(String email, String password);

    void update(Region region);

    List<Region> listByParentId(Long parentId);

    void updateState(Integer state,Long id);

    List<Region> listAll(Integer state);
}
