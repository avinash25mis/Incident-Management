package com.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserVO implements Serializable {

    private Long id;
    @NotNull
    private String username;
    private String password;
    private String firstName;
    private String lastName;

}
