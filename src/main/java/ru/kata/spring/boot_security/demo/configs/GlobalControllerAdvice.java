package ru.kata.spring.boot_security.demo.configs;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addAttributes(HttpServletRequest request, Model model) {

        String uri = request.getRequestURI();
        String activePage = "";
        String pageTitle = "Default";

        if (uri.contains("dashboard")) {
            activePage = "dashboard";
        } else if (uri.contains("users")) {
            activePage = "users";
        } else if (uri.contains("settings")) {
            activePage = "settings";
        }

        model.addAttribute("activePage", activePage);
        model.addAttribute("currentUri", uri);
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("pageTitle", pageTitle);
    }

    @ModelAttribute("currentUserRoles")
    public List<String> getCurrentUserRoles() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return Collections.emptyList();
        }

        return auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(role -> role.replace("ROLE_", ""))
                .collect(Collectors.toList());
    }
}