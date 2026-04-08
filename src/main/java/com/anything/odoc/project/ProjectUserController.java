package com.anything.odoc.project;

import com.anything.odoc.project.dao.AutoLoginDao;
import com.anything.odoc.project.vo.ProjectUserVO;
import com.anything.odoc.utils.TokenUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RequiredArgsConstructor
@RestController
public class ProjectUserController {

    private final ProjectUserService projectUserService;
    private final AutoLoginDao autoLoginDao;

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
    public ResponseEntity<ProjectUserVO> userLogin(@RequestBody ProjectUserVO projectUserVO, HttpSession session, HttpServletResponse response) {
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

            // 자동로그인 체크 시 remember-me 토큰 발급
            if ("Y".equals(projectUserVO.getRememberMe())) {
                String token = TokenUtils.generateRememberMeToken();

                LocalDateTime expireDt = LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusDays(30);

                autoLoginDao.upsertAutoLoginToken(loginUser.getUserId(), token, expireDt);

                Cookie cookie = new Cookie("remember-me", token);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24 * 30);

                response.addCookie(cookie);
            }

            return ResponseEntity.ok(loginUser);
        }
    }

    @GetMapping("/sessionUser")
    public ResponseEntity<ProjectUserVO> sessionUser(
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        ProjectUserVO loginUser = (ProjectUserVO) session.getAttribute("loginUser");

        if (loginUser != null) {
            return ResponseEntity.ok(loginUser);
        }

        String rememberMeToken = getCookieValue(request, "remember-me");

        if (rememberMeToken == null || rememberMeToken.isBlank()) {
            return ResponseEntity.status(401).build();
        }

        String userId = autoLoginDao.selectUserIdByToken(rememberMeToken);
        LocalDateTime expireDt = autoLoginDao.selectExpireDtByToken(rememberMeToken);

        if (userId == null || expireDt == null) {
            expireRememberMeCookie(response);
            return ResponseEntity.status(401).build();
        }

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        if (expireDt.isBefore(now)) {
            autoLoginDao.deleteAutoLoginByToken(rememberMeToken);
            expireRememberMeCookie(response);
            return ResponseEntity.status(401).build();
        }

        ProjectUserVO user = projectUserService.selectUserById(userId);

        if (user == null) {
            autoLoginDao.deleteAutoLoginByToken(rememberMeToken);
            expireRememberMeCookie(response);
            return ResponseEntity.status(401).build();
        }

        session.setAttribute("loginUser", user);

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(user, null, null);

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(auth);
        session.setAttribute("SPRING_SECURITY_CONTEXT", context);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/userLogout")
    public ResponseEntity<?> logout(
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        String token = getCookieValue(request, "remember-me");

        if (token != null && !token.isBlank()) {
            autoLoginDao.deleteAutoLoginByToken(token);
        }

        expireRememberMeCookie(response);

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

    private String getCookieValue(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null) {
            return null;
        }

        for (Cookie cookie : request.getCookies()) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private void expireRememberMeCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("remember-me", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
