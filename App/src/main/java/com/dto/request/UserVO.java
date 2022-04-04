package com.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserVO implements Serializable {

    @NotNull
    private String username;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String password;
    private String firstName;
    private String lastName;

}
