package com.example.demo.data;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserData {
    private static final long serialVersionUID = 1234L;

    @Id
    private String username;

    private int age;
}
