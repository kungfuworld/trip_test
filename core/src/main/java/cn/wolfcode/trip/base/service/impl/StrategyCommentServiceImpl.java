package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.mapper.StrategyCommentMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyCommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyCommentServiceImpl implements IStrategyCommentService {

    @Autowired
    private StrategyCommentMapper strategyCommentMapper;

    @Override
    public PageInfo<StrategyComment> queryForList(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrder());
        List<StrategyComment> list = strategyCommentMapper.queryForList(qo);
        return new PageInfo<>(list);
    }

    @Override
    public void save(StrategyComment strategyComment) {
        strategyCommentMapper.insert(strategyComment);
    }
}
