package com.anything.odoc.project;

import com.anything.odoc.project.dao.ProjectMainDao;
import com.anything.odoc.project.vo.ProjectMainVO;
import com.anything.odoc.project.vo.ProjectUserVO;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.*;

@Service
public class ProjectUserService {

    private final ProjectMainDao projectMainDao;

    private final PasswordEncoder passwordEncoder;

    public ProjectUserService(ProjectMainDao projectMainDao, PasswordEncoder passwordEncoder) {
        this.projectMainDao = projectMainDao;
        this.passwordEncoder = passwordEncoder;
    }

    public int userRegister(ProjectUserVO projectUserVO) {
        projectUserVO.setUserPw(passwordEncoder.encode(projectUserVO.getUserPw()));
        return projectMainDao.userRegister(projectUserVO);
    }

    public int userIdCheck(ProjectUserVO projectUserVO) {
        return projectMainDao.userIdCheck(projectUserVO);
    }

    public ProjectUserVO userLogin(ProjectUserVO projectUserVO) {
        ProjectUserVO loginUser = projectMainDao.userLogin(projectUserVO);
        if(loginUser == null) {
            return null;
        }
        if(!passwordEncoder.matches(projectUserVO.getUserPw(), loginUser.getUserPw())) {
            return null;
        }
        return loginUser;
    }

    public int updateAlert(ProjectUserVO projectUserVO) {
        return projectMainDao.updateAlert(projectUserVO);
    }
}
