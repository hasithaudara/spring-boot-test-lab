package com.practice.spring.SpringBootPracticeLab.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    @NotBlank(message = "stringValue has to be present")
    @Size(max = 15, message = "size violation on firstname field")
    private String firstName;
    @NotBlank(message = "stringValue has to be present")
    @Size(max = 15, message = "size violation on lastname field")
    private String lastName;
    @NotBlank(message = "stringValue has to be present")
    @Email
    private String email;
}
