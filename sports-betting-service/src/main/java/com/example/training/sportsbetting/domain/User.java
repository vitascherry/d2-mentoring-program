package com.example.training.sportsbetting.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String email;

    private String passwordHash;

    private boolean active;
}
