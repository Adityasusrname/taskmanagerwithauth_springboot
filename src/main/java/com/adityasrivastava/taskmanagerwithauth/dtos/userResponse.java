package com.adityasrivastava.taskmanagerwithauth.dtos;

import lombok.Data;

@Data

public class userResponse {

    private long id;
    private String username;

    private String email;

    private String token;
}
