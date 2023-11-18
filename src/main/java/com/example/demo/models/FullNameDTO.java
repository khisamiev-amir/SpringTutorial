package com.example.demo.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullNameDTO {
    private String name;
    private String lastName;
    private String middleName;
}
