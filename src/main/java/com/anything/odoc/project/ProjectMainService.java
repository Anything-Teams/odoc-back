package com.anything.odoc.project;

import com.anything.odoc.project.dao.ProjectMainDao;
import com.anything.odoc.project.vo.ProjectMainVO;
import com.anything.odoc.project.vo.ProjectMonthVO;
import com.anything.odoc.project.vo.ThemaVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectMainService {

    private final ProjectMainDao projectMainDao;

    public ProjectMainService(ProjectMainDao projectMainDao) {
        this.projectMainDao = projectMainDao;
    }

    public List<ProjectMainVO> getProjectMainList(ProjectMainVO projectMainVO) {
        return projectMainDao.getProjectMainList(projectMainVO);
    }

    public ProjectMainVO getProjectMain(ProjectMainVO projectMainVO) {
        return projectMainDao.getProjectMain(projectMainVO);
    }

    public List<Map<String, Object>> getHistYearList(ProjectMainVO projectMainVO) {
        List<ProjectMainVO> list = projectMainDao.getHistYearList(projectMainVO);
        List<Map<String, Object>> result = new ArrayList<>();

        if(!list.isEmpty()) {
            Map<String, List<ProjectMainVO>> grouped =
                    list.stream().collect(Collectors.groupingBy(ProjectMainVO::getYear));

            List<String> sortedYears = grouped.keySet().stream()
                    .sorted(Comparator.reverseOrder())
                    .toList();

            for (String year : sortedYears) {
                Map<String, Object> map = new HashMap<>();
                map.put("year", year);
                map.put("months", grouped.get(year));
                result.add(map);
            }
        }

        return result;
    }

    public Map<String, Object> getHistMonth(ProjectMainVO projectMainVO) {
        ProjectMonthVO data = projectMainDao.getHistMonth(projectMainVO);

        String[] checkedDays = data.getCheckedDates().split(",");
        String[] checkedTimestamps = data.getCheckedTimestamps().split(",");
        String[] memos = (data.getCheckedMemos() != null && !data.getCheckedMemos().isEmpty())
                ? data.getCheckedMemos().split(",")
                : new String[0];
        Map<String, Object> dateTime = new HashMap<>();
        dateTime.put("checkedDays", checkedDays);
        dateTime.put("checkedTimestamps", checkedTimestamps);

        Map<String, Object> result = new HashMap<>();
        result.put("title", data.getOdocNm());
        result.put("createdDay", data.getCreatedDay());
        result.put("dateTime", dateTime);
        result.put("memos", memos);
        result.put("odocRate", data.getProgress());
        result.put("odocThemaType", data.getOdocThemaType());
        result.put("odocType", data.getOdocType());

        return result;
    }

    public ProjectMainVO getProjectName(ProjectMainVO projectMainVO) {
        return projectMainDao.getProjectName(projectMainVO);
    }

    public int updateProject(ProjectMainVO projectMainVO) {
        return projectMainDao.updateProject(projectMainVO);
    }

    public int insertProject(ProjectMainVO projectMainVO) {
        return projectMainDao.insertProject(projectMainVO);
    }

    @Transactional
    public int commitProject(ProjectMainVO projectMainVO) {
        int result = projectMainDao.commitProject(projectMainVO);

        if(result > 0) {
            projectMainDao.updateStream(projectMainVO);
            projectMainDao.updateUserStreamMax(projectMainVO);
        }

        return result;
    }

    public List<ProjectMainVO> selectUserThemaList(ProjectMainVO projectMainVO) {
        List<ProjectMainVO> result = projectMainDao.selectUserThemaList(projectMainVO);
        return result != null ? result : new ArrayList<>();
    }

    public int insertUserThema(ProjectMainVO projectMainVO) {
        return projectMainDao.insertUserThema(projectMainVO);
    }

    public List<ThemaVO> selectThemaList(ThemaVO themaVO) {
        return projectMainDao.selectThemaList(themaVO);
    }

    public String codeRegist(ThemaVO themaVO) {
        String result = "";

        List<ThemaVO> codeThema = projectMainDao.selectThemaList(themaVO);

        if(!codeThema.isEmpty()) {
            ProjectMainVO projectMainVO = new ProjectMainVO();
            projectMainVO.setUserId(themaVO.getUserId());
            projectMainVO.setThemaId(codeThema.getFirst().getThemaId());
            projectMainVO.setThemaConfirmYn("Y");

            if(projectMainDao.selectUserThemaList(projectMainVO).isEmpty()) {
                projectMainDao.insertUserThema(projectMainVO);

                result = "[" + codeThema.getFirst().getThemaNm() + "] 를 받았습니다!";
            } else {
                result = "이미 등록이 된 코드입니다";
            }
        } else {
            result = "해당 코드는 없는 코드입니다";
        }
        return result;
    }
    
    public int updateMemo(ProjectMainVO projectMainVO) {
        return projectMainDao.updateMemo(projectMainVO);
    }
}
