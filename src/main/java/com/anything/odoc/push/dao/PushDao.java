package com.anything.odoc.push.dao;

import com.anything.odoc.push.vo.PushTargetVO;
import com.anything.odoc.push.vo.PushTokenVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PushDao {
    int insertPushToken(PushTokenVO pushTokenVO);
    List<String> selectPushTokens(@Param("userId") String userId);
    List<PushTargetVO> selectPushTargets(@Param("nowHm") String nowHm);
}
