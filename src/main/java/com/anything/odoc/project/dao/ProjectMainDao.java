package com.anything.odoc.project.dao;

import com.anything.odoc.project.vo.ProjectMainVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMainDao {

    List<ProjectMainVO> getProjectMainList(ProjectMainVO projectMainVO);
}
