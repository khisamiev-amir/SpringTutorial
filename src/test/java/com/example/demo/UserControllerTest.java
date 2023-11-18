package com.example.demo;

import com.example.demo.controllers.UserController;
import com.example.demo.models.FullNameDTO;
import com.example.demo.models.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@SpringBootTest
public class UserControllerTest {
    //здесь есть лист, т.к. в классе UserController он создается сразу
    private UserDTO userDto;

    @Test
    public void testCreateUser() {
        UserController userController = new UserController();
        userDto = new UserDTO(1L, new FullNameDTO("Имя", "Фамилия", "Отчество"), new ArrayList<String>());
        Assertions.assertEquals(userDto, userController.createUser(userDto));

    }

    @Test
    public void testUpdateUser() {
        UserController userController = new UserController();
        userDto = new UserDTO(1L, new FullNameDTO("Имя", "Фамилия", "Отчество"), new ArrayList<String>());
        UserDTO userDto2 = new UserDTO(2L, new FullNameDTO("Имя2", "Фамилия2", "Отчество2"), new ArrayList<String>());
        userController.createUser(userDto);
        userController.updateUser(userDto2);

        Optional <UserDTO>  optionalUserDTO = userController.getUsers().stream()
                .filter(user -> user.getId() == userDto2.getId())
                .findFirst();
        if (optionalUserDTO.isPresent()) {
            UserDTO userDTO1 = optionalUserDTO.get();

            Assertions.assertEquals(userDto2, userDTO1);
        }
    }
}



