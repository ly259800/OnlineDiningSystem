package com.ly.hotel.mapper;

import com.ly.hotel.po.Dinnertable;
import com.ly.hotel.po.DinnertableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DinnertableMapper {
    int countByExample(DinnertableExample example);

    int deleteByExample(DinnertableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Dinnertable record);

    int insertSelective(Dinnertable record);

    List<Dinnertable> selectByExample(DinnertableExample example);

    Dinnertable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Dinnertable record, @Param("example") DinnertableExample example);

    int updateByExample(@Param("record") Dinnertable record, @Param("example") DinnertableExample example);

    int updateByPrimaryKeySelective(Dinnertable record);

    int updateByPrimaryKey(Dinnertable record);
}