package com.adityasrivastava.taskmanagerwithauth.daos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class loginUserRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;

}
