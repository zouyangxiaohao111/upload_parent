package cn.xy.mapper;

import cn.xy.domain.TbApk;
import cn.xy.domain.TbApkExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbApkMapper {
    int countByExample(TbApkExample example);

    int deleteByExample(TbApkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbApk record);

    int insertSelective(TbApk record);

    List<TbApk> selectByExample(TbApkExample example);

    TbApk selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbApk record, @Param("example") TbApkExample example);

    int updateByExample(@Param("record") TbApk record, @Param("example") TbApkExample example);

    int updateByPrimaryKeySelective(TbApk record);

    int updateByPrimaryKey(TbApk record);
}