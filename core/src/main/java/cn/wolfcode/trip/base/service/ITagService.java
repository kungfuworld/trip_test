package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ITagService {
//    PageInfo<StrategyComment> queryForList(QueryObject qo);

    void save(Tag tag);

    List<String> getTagsByStrategyId(Long strategyId);
}
