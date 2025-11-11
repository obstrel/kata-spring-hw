package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer  {
    @Autowired
    private UserService userService;

    @EventListener(ApplicationReadyEvent.class)
    public void onAppReady() {
        userService.generateDefaultUsers();
    }
}
