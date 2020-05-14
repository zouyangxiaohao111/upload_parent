package cn.xy.mapper;

import cn.xy.domain.TbApkItem;
import cn.xy.domain.TbApkItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbApkItemMapper {
    int countByExample(TbApkItemExample example);

    int deleteByExample(TbApkItemExample example);

    int deleteByPrimaryKey(Integer childId);

    int insert(TbApkItem record);

    int insertSelective(TbApkItem record);

    List<TbApkItem> selectByExample(TbApkItemExample example);

    TbApkItem selectByPrimaryKey(Integer childId);

    int updateByExampleSelective(@Param("record") TbApkItem record, @Param("example") TbApkItemExample example);

    int updateByExample(@Param("record") TbApkItem record, @Param("example") TbApkItemExample example);

    int updateByPrimaryKeySelective(TbApkItem record);

    int updateByPrimaryKey(TbApkItem record);
}