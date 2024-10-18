package com.bootcodingtask.rating_functionality.models;

import lombok.Data;

@Data
public class UsersResponse {
    private Integer user_id;
    private String username;
    private String email;
    private String createdAt;

}
