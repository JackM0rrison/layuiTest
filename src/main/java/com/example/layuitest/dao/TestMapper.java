package com.example.layuitest.dao;

import com.example.layuitest.domain.test_table;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(test_table record);

    int insertSelective(test_table record);

    test_table selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(test_table record);

    int updateByPrimaryKey(test_table record);

    List<test_table> selectAll();
}