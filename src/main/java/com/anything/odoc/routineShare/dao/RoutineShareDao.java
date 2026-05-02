package com.anything.odoc.routineShare.dao;

import com.anything.odoc.routineShare.dto.RoutineShareCreateReq;
import com.anything.odoc.routineShare.dto.RoutineShareImportRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoutineShareDao {

    int existsShareCode(@Param("shareCode") String shareCode);

    int insertSharedRoutine(
            @Param("shareCode") String shareCode,
            @Param("req") RoutineShareCreateReq req
    );

    RoutineShareImportRes selectSharedRoutine(@Param("shareCode") String shareCode);

    int increaseDownloadCount(@Param("shareCode") String shareCode);
}