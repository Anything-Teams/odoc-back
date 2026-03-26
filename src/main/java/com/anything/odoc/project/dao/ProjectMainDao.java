package com.anything.odoc.project.dao;

import com.anything.odoc.project.vo.ProjectMainVO;
import com.anything.odoc.project.vo.ProjectMonthVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMainDao {

    List<ProjectMainVO> getProjectMainList(ProjectMainVO projectMainVO);

    ProjectMainVO getProjectMain(ProjectMainVO projectMainVO);

    List<ProjectMainVO> getHistYearList(ProjectMainVO projectMainVO);

    ProjectMonthVO getHistMonth(ProjectMainVO projectMainVO);

    ProjectMainVO getProjectName(ProjectMainVO projectMainVO);

    int updateProject(ProjectMainVO projectMainVO);

    int insertProject(ProjectMainVO projectMainVO);

    int commitProject(ProjectMainVO projectMainVO);

    ProjectMainVO getTempUser(ProjectMainVO projectMainVO);

    int updateTempUser(ProjectMainVO projectMainVO);
}
