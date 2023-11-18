package com.example.demo.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    private Long id;
    private FullNameDTO fullName;
    private List<String> email;

    public UserDTO(Long id, FullNameDTO fullName) {
        this.id = id;
        this.fullName = fullName;
    }

}
