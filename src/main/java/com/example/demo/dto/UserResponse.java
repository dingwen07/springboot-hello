package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

import com.example.demo.data.UserData;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    @Serial
    private static final long serialVersionUID = 123456L;

    private String message;
    private int code;

    private UserData data;
}
