package com.example.demo.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.dao.UserDAO;
import com.example.demo.domain.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final Logger log = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
    @Autowired
    private UserDAO udao;

    public CustomAuthenticationSuccessHandler(String defaultTargetUrl) {
        log.info(">>>>>>sucesshandler 진입");
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        if (request.getParameter("email") != null) {
            UserVO user = udao.getUserfindByEmail(request.getParameter("email"));
            user.setPassword(null);
            // user.setRoles(roles);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            log.info(">>>>>>>>>login session: " + session);
        }
        getRedirectStrategy().sendRedirect(request, response, "/main");
    }
}
