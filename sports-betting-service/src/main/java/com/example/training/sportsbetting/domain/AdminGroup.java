package com.example.training.sportsbetting.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(exclude = "admins")
public class AdminGroup {

    private SportEventType sportEventTypeTarget;

    private List<Admin> admins;
}
