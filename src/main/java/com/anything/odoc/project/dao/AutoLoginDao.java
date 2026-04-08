package com.anything.odoc.project.dao;

import java.time.LocalDateTime;

import com.anything.odoc.project.vo.ProjectUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AutoLoginDao {

    void upsertAutoLoginToken(
            @Param("userId") String userId,
            @Param("token") String token,
            @Param("expireDt") LocalDateTime expireDt
    );

    String selectUserIdByToken(@Param("token") String token);

    LocalDateTime selectExpireDtByToken(@Param("token") String token);

    void deleteAutoLoginByUserId(@Param("userId") String userId);

    void deleteAutoLoginByToken(@Param("token") String token);

}