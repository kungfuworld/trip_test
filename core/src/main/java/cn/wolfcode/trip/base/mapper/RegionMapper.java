package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.query.QueryObject;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface RegionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Region record);

    Region selectByPrimaryKey(Long id);

    /**
     * 如果SQL中需要用到if或choose等中的text进行判断，此时必须要有注解
     * @param parentId
     * @return
     */
    List<Region> selectByParentId(@Param("parentId")Long parentId);

    int updateByPrimaryKey(Region record);

    void updateState(@Param("state") Integer state,@Param("id") Long id);

    List<Region> selectAll(@Param("state") Integer state);
}