package com.example.ecommerce.config;

import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        User userDetails = (User) authentication.getPrincipal();

        String redirectURL = request.getContextPath();

        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            redirectURL = "";
        } else if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            redirectURL = "admin";
        } else if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_VENDOR"))) {
            redirectURL = "vendor";
        }

        response.sendRedirect(redirectURL);
        //super.onAuthenticationSuccess(request, response, authentication);
    }
}
