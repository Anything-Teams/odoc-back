package com.anything.odoc.test.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDao {

    String selectName();
}