package com.anything.odoc.project;

import com.anything.odoc.project.dao.ProjectMainDao;
import com.anything.odoc.project.vo.ProjectMainVO;
import com.anything.odoc.project.vo.ProjectMonthVO;
import org.springframework.stereotype.Service;

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
        Map<String, Object> dateTime = new HashMap<>();
        dateTime.put("checkedDays", checkedDays);
        dateTime.put("checkedTimestamps", checkedTimestamps);

        Map<String, Object> result = new HashMap<>();
        result.put("title", data.getOdocNm());
        result.put("createdDay", data.getCreatedDay());
        result.put("dateTime", dateTime);
        result.put("odocRate", data.getProgress());

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

    public int commitProject(ProjectMainVO projectMainVO) {
        return projectMainDao.commitProject(projectMainVO);
    }
}
