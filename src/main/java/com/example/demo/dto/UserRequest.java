package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 12345L;
    @NotBlank(message = "username must not be blank")
    private String username;
    @NotBlank(message = "age must not be blank")
    private int age;
    private String code;
}
