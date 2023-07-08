package org.userService.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.userService.entity.User;
import org.userService.request.RequestLogin;
import org.userService.response.UserResponse;
import org.userService.service.UserService;

import java.util.List;

@RestController
@Slf4j
@Api(tags = {"UserController ", "UserController"})
public class UserController {
    @Autowired
    private UserService userServiceWebClient;

    @PostMapping("/login")
   @Tag(name="getUserByLogin ", description="Получение пользователя по логину")
    private ResponseEntity<UserResponse> getUserByLogin(@RequestBody RequestLogin login) {
        UserResponse userResponse = userServiceWebClient.getUserByLogin(login);
        log.info("Get getUserByLogin from UserController: {}", login);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
    @GetMapping("/all")
    @Tag(name="getAllUsers ", description="Получение всех пользователей")
    private ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userServiceWebClient.getAllUsers();
        log.info("Get ALL USERS from UserController: {}", userList);
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/user/{id}")
    @Tag(name="getUserDetails ", description="Получение информации пользователя по ID")
    private ResponseEntity<UserResponse> getUserDetails(@PathVariable("id") Long id) {
        UserResponse userResponse = userServiceWebClient.getUserById(id);
        log.info("Get User Details with id {} from UserController: {}", id, userResponse);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PutMapping("/rolechange/{id}")
    @Tag(name="updateUserRole ", description="Изменение роли по ID пользователя")
    public ResponseEntity<UserResponse> updateUserRole(@PathVariable("id") Long id, @RequestBody User user) {
        UserResponse userResponse = userServiceWebClient.changeRoleForUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @PostMapping("/create")
    @Tag(name="createUser ", description="Создание нового пользователя")
    private ResponseEntity<User> createUser(@RequestBody User user) {
        User userResponse = userServiceWebClient.saveUser(user);
        log.info("CREATE Users from UserController: {}", userResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PutMapping("/update/{id}")
    @Tag(name="updateUser ", description="Изменение пользователя")
    private ResponseEntity<UserResponse> updateUser(@PathVariable("id")Long id, @RequestBody User user) {
        UserResponse userResponse = userServiceWebClient.updateUser(id, user);
        log.info("UPDATE Users with id: {} from UserController: {}",id, userResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @DeleteMapping("/delete/{id}")
    @Tag(name="deleteUserById ", description="Удаление пользователя по ID")
    private ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {
        boolean deleteUserById =  userServiceWebClient.deleteUserById(id);
        log.info("DELETE User with id {} from UserController", id);
        if (deleteUserById) {
            return new ResponseEntity<>(("User deleted - User ID:" + id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(("User deletion failed - User ID:" + id), HttpStatus.BAD_REQUEST);
        }
    }


}
