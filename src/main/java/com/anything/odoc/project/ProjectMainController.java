package com.anything.odoc.project;

import com.anything.odoc.project.vo.ProjectMainVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProjectMainController {

    @Autowired
    ProjectMainService projectMainService;

    @PostMapping("/getProjectMainList")
    public List<ProjectMainVO> getProjectMainList(@RequestBody ProjectMainVO projectMainVO) {
        return projectMainService.getProjectMainList(projectMainVO);
    }

    @PostMapping("/getProjectMain")
    public ProjectMainVO getProjectMain(@RequestBody ProjectMainVO projectMainVO) {
        return projectMainService.getProjectMain(projectMainVO);
    }

    @PostMapping("/getHistYearList")
    public List<Map<String, Object>> getHistYearList(@RequestBody ProjectMainVO projectMainVO) {
        return projectMainService.getHistYearList(projectMainVO);
    }

    @PostMapping("/getHistMonth")
    public Map<String, Object> getHistMonth(@RequestBody ProjectMainVO projectMainVO) {
        return projectMainService.getHistMonth(projectMainVO);
    }

    @PostMapping("/getProjectName")
    public ProjectMainVO getProjectName(@RequestBody ProjectMainVO projectMainVO) {
        return projectMainService.getProjectName(projectMainVO);
    }

    @PostMapping("/updateProject")
    public ResponseEntity<Integer> updateProject(@RequestBody ProjectMainVO projectMainVO) {
        int result = projectMainService.updateProject(projectMainVO);
        if (result > 0) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/insertProject")
    public ResponseEntity<Integer> insertProject(@RequestBody ProjectMainVO projectMainVO) {
        int result = projectMainService.insertProject(projectMainVO);
        if (result > 0) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/commitProject")
    public ResponseEntity<Integer> commitProject(@RequestBody ProjectMainVO projectMainVO) {
        int result = projectMainService.commitProject(projectMainVO);
        if (result > 0) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/healthCheck)")
    public String healthCheck() {
        return "ok";
    }
}
