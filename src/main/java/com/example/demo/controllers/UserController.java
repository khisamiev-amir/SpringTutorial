package com.example.demo.controllers;

import com.example.demo.models.UserDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UserController {
    public List<UserDTO> list = new ArrayList<>();

    @PostMapping(value = "userController/createUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO createUser(@RequestBody UserDTO user) {
        list.add(user);
        return user;

    }

    @GetMapping(value = "userController/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getUsers() {
        return list; // кажется, что здесь надо понять, как вернуть весь лист, но это умеет spring.

    }

    @GetMapping(value = "userController/getUser/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUser(@PathVariable Long userId) {
        Optional<UserDTO> userOptional = list.stream()
                .filter(user -> user.getId() == userId)
                .findFirst();
        return userOptional.orElse(null); // кажется, что здесь надо понять, как вернуть весь лист, но это умеет spring.
    }

    @PutMapping(value = "userController/updateUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getId() == null ) {
            // Проверка на пустые данные пользователя - идентификатор
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Возвращает HTTP статус 400 Bad Request
        }


        Optional<UserDTO> userToUpdate = list.stream()
                .filter(user -> user.getId() == userDTO.getId())
                .findFirst();

        if (userToUpdate.isPresent()) {
            userToUpdate.get().setId(userDTO.getId());
          //  userToUpdate.get() т.к. userToUpdate это объект класса Optional, мы достаем из него UserDTO объект .setUsername(updatedUser.getUsername());
            userToUpdate.get().setEmail(userDTO.getEmail());
            userToUpdate.get().setFullName(userDTO.getFullName());
            return ResponseEntity.ok(userToUpdate.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Если пользователь с заданным ID не найден

    }


    @DeleteMapping(value = "userController/deleteUser/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)//в PathVariable именно благодаря указыванию userId в userController подставится значение
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long userId) {
        Optional<UserDTO> userToUpdate = list.stream()
                .filter(user -> user.getId() == userId)
                .findFirst();

        if (userToUpdate.isPresent()) {
            list.remove(userToUpdate.get()); //т.к. подаем найденный объект, remove сам поймет кого удалить, по своеё логике, которая у него там есть.
            //  userToUpdate.get() т.к. userToUpdate это объект класса Optional, мы достаем из него UserDTO объект .setUsername(updatedUser.getUsername());
            return ResponseEntity.ok(userToUpdate.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Если пользователь с заданным ID не найден

    }

}



