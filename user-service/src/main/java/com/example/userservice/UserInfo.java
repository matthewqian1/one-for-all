package com.example.userservice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {
    String username;
    String password;
}
