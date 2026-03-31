package com.anything.odoc.project;

import com.anything.odoc.project.vo.NoticeVO;
import com.anything.odoc.project.vo.ProjectUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectAdminController {

    @Autowired
    ProjectAdminService projectAdminService;

    @PostMapping("/insertNotice")
    public ResponseEntity<Integer> insertNotice(@RequestBody NoticeVO noticeVO){
        int result = projectAdminService.insertNotice(noticeVO);
        if (result > 0) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/selectNotice")
    public ResponseEntity<NoticeVO> selectNotice(@RequestBody NoticeVO noticeVO){
        NoticeVO result = new NoticeVO();

        result = projectAdminService.selectNotice(noticeVO);

        if (result == null) {
            return ResponseEntity.ok(new NoticeVO());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/userList")
    public List<ProjectUserVO> userList(ProjectUserVO projectUserVO) {
        return projectAdminService.userList(projectUserVO);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<Integer> updateUser(@RequestBody ProjectUserVO projectUserVO) {
        int result = projectAdminService.updateUser(projectUserVO);
        if (result > 0) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<Integer> deleteUser(@RequestBody ProjectUserVO projectUserVO) {
        int result = projectAdminService.deleteUser(projectUserVO);
        if (result > 0) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
