package com.example.training.sportsbetting.domain;

import lombok.Data;

import java.util.List;

@Data
public class AdminGroup {

    private SportEventType sportEventTypeTarget;

    private List<Admin> admins;
}
