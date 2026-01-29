package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserControllerRest {
    private final UserService userService;

    public UserControllerRest(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.removeUserById(id);
            return ResponseEntity.ok().build();  // HTTP 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка удаления: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        User user = userService.updateUserFromRequestBody(id, updates);


        // Возвращаем обновленного пользователя
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("firstName", user.getFirstName());
        response.put("lastName", user.getLastName());
        response.put("email", user.getEmail());
        response.put("roles", user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable Long id,
//                                        @RequestBody User userData) {
//        User user = userService.findUserById(id);
//
//        // Копируем только разрешённые поля
//        user.setFirstName(userData.getFirstName());
//        user.setLastName(userData.getLastName());
//        user.setEmail(userData.getEmail());
////        userService.assignRoles(user, userData.getRoles());
//
//        // Не копируем пароль, id и другие чувствительные поля!
//
//        userService.saveUser(user);
//        return ResponseEntity.ok(user);
//    }
}
