package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    void updateCommentTagRelation(@Param("comId") Long comId, @Param("tagId")Long tagId);

    Tag selectByPrimaryKey(Long id);

    List<Tag> selectAll();

    int updateByPrimaryKey(Tag record);

    List<String> selectTagsByStrategyId(Long strategyId);
}