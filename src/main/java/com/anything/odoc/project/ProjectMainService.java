package com.anything.odoc.project;

import com.anything.odoc.project.dao.ProjectMainDao;
import com.anything.odoc.project.vo.ProjectMainVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMainService {

    private final ProjectMainDao projectMainDao;

    public ProjectMainService(ProjectMainDao projectMainDao) {
        this.projectMainDao = projectMainDao;
    }

    public List<ProjectMainVO> getProjectMainList(ProjectMainVO projectMainVO) {
        return projectMainDao.getProjectMainList(projectMainVO);
    }
}
