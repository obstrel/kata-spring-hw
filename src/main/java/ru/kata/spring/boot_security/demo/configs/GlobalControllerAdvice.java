package ru.kata.spring.boot_security.demo.configs;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addAttributes(HttpServletRequest request, Model model) {
        // Определяем активную страницу по URI
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
}