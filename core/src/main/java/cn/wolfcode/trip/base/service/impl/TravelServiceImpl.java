package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.TravelContentMapper;
import cn.wolfcode.trip.base.mapper.TravelMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TravelServiceImpl implements ITravelService {

    @Autowired
    private TravelMapper travelMapper;

    @Autowired
    private TravelContentMapper travelContentMapper;
    @Override
    public void save(Travel travel) {
        //将作者id，编写时间，最后更新时间补充完整
        User user = new User();
        user.setId(UserContext.getCurrentUser().getId());
        travel.setAuthor(user);
        travel.setLastUpdateTime(new Date());
        if(travel.getId()==null){
            travel.setCreateTime(new Date());
            travelMapper.insert(travel);
            //维护和文本表的关系
            Long id = travel.getId();
            TravelContent travelContent = travel.getTravelContent();
            travelContent.setId(id);
            travelContentMapper.insert(travelContent);

        } else{
            travelMapper.updateByPrimaryKey(travel);
        }
    }

    @Override
    public void delete(Long id) {
        travelMapper.deleteByPrimaryKey(id);
    }


    @Override
    public void update(Travel travel) {
        travelMapper.updateByPrimaryKey(travel);
    }

    @Override
    public Travel get(Long id) {
        return travelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Travel> listAll() {
        return travelMapper.selectAll();
    }

    @Override
    public PageInfo<Travel> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(), qo.getOrder());
        List<Travel> list = travelMapper.selectForList(qo);
        return new PageInfo<>(list);
    }

    @Override
    public TravelContent getTravelContentById(Long id) {
        return travelContentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void changeState(Travel travel) {
        if(Travel.STATE_RELEASE == travel.getState()){
            travel.setReleaseTime(new Date());
        }
        travelMapper.changeState(travel);
    }
}
