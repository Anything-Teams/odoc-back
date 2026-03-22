package com.anything.odoc.project;

import com.anything.odoc.project.vo.ProjectMainVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectMainController {

    @Autowired
    ProjectMainService projectMainService;

    @PostMapping("/getProjectMainList")
    public List<ProjectMainVO> getProjectMainList(@RequestBody ProjectMainVO projectMainVO) {
        return projectMainService.getProjectMainList(projectMainVO);
    }
}
