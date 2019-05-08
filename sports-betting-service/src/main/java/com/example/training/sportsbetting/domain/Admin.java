package com.example.training.sportsbetting.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class Admin extends User {

    private List<AdminGroup> groups;

    private Set<SportEventType> managingSportEventTypes;
}
