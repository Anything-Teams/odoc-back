package com.anything.odoc.project;

import com.anything.odoc.project.vo.ProjectMainVO;
import com.anything.odoc.project.vo.ProjectUserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectUserController {

    @Autowired
    ProjectUserService projectUserService;

    @PostMapping("/userRegister")
    public ResponseEntity<Integer> userRegister(@RequestBody ProjectUserVO projectUserVO){
        int result = projectUserService.userRegister(projectUserVO);
        if (result > 0) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/userIdCheck")
    public int userIdCheck(@RequestBody ProjectUserVO projectUserVO){
        return projectUserService.userIdCheck(projectUserVO);
    }

    @PostMapping("/userLogin")
    public ResponseEntity<ProjectUserVO> userLogin(@RequestBody ProjectUserVO projectUserVO, HttpSession session) {
        ProjectUserVO loginUser = projectUserService.userLogin(projectUserVO);

        if (loginUser == null) {
            return ResponseEntity.badRequest().build();
        } else {
            // 세션에 로그인한 사용자 정보를 저장, 세션이름은 loginUser
            session.setAttribute("loginUser", loginUser);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loginUser, null, null);
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(auth);

            session.setAttribute("SPRING_SECURITY_CONTEXT", context);

            return ResponseEntity.ok(loginUser);
        }
    }

    @GetMapping("/sessionUser")
    public ResponseEntity<ProjectUserVO> sessionUser(HttpSession session) {
        ProjectUserVO loginUser = (ProjectUserVO) session.getAttribute("loginUser");

        if (loginUser == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(loginUser);
    }

    @PostMapping("/userLogout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(1);
    }

    @PostMapping("/updateAlert")
    public ResponseEntity<Integer> updateAlert(@RequestBody ProjectUserVO projectUserVO) {
        int result = projectUserService.updateAlert(projectUserVO);
        if (result > 0) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
