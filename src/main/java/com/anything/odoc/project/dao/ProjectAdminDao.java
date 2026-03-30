package com.anything.odoc.project.dao;

import com.anything.odoc.project.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectAdminDao {

    int insertNotice(NoticeVO noticeVO);

    int updateNotice(NoticeVO noticeVO);

    NoticeVO selectNotice(NoticeVO noticeVO);

}
