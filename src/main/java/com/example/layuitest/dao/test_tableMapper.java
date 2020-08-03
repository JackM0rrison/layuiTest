package com.example.layuitest.dao;

import com.example.layuitest.domain.test_table;

public interface test_tableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(test_table record);

    int insertSelective(test_table record);

    test_table selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(test_table record);

    int updateByPrimaryKey(test_table record);
}