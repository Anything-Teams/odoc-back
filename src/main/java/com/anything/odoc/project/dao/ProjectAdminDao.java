package com.anything.odoc.project.dao;

import com.anything.odoc.project.vo.NoticeVO;
import com.anything.odoc.project.vo.ProjectUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectAdminDao {

    int insertNotice(NoticeVO noticeVO);

    int updateNotice(NoticeVO noticeVO);

    NoticeVO selectNotice(NoticeVO noticeVO);

    List<ProjectUserVO> userList(ProjectUserVO projectUserVO);

    int updateUser(ProjectUserVO projectUserVO);

    int deleteUser(ProjectUserVO projectUserVO);
}
