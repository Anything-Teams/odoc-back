package com.anything.odoc.project;

import com.anything.odoc.project.dao.ProjectAdminDao;
import com.anything.odoc.project.dao.ProjectMainDao;
import com.anything.odoc.project.vo.NoticeVO;
import com.anything.odoc.project.vo.ProjectUserVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectAdminService {

    private final ProjectAdminDao projectadminDao;

    public ProjectAdminService(ProjectAdminDao projectadminDao) {
        this.projectadminDao = projectadminDao;
    }

    public int insertNotice(NoticeVO param) {
        NoticeVO noticeVO = projectadminDao.selectNotice(param);
        int result = 0;

        param.setNoticeYn(param.getBeforeNoticeYn());
        if(noticeVO != null) result = projectadminDao.updateNotice(param);
        else result = projectadminDao.insertNotice(param);

        return result;
    }

    public NoticeVO selectNotice(NoticeVO noticeVO) {
        return projectadminDao.selectNotice(noticeVO);
    }

    public List<ProjectUserVO> userList(ProjectUserVO projectUserVO) {
        return projectadminDao.userList(projectUserVO);
    }

    public int updateUser(ProjectUserVO projectUserVO) {
        return projectadminDao.updateUser(projectUserVO);
    }

    public int deleteUser(ProjectUserVO projectUserVO) {
        return projectadminDao.deleteUser(projectUserVO);
    }
}
