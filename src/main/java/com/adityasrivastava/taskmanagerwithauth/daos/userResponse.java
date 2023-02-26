package com.adityasrivastava.taskmanagerwithauth.daos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data

public class userResponse {

    private long id;
    private String username;

    private String email;

    private String token;
}
