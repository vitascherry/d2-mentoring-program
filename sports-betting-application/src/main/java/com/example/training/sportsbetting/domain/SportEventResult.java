package com.example.training.sportsbetting.domain;

import lombok.Data;

import java.util.List;

@Data
public class SportEventResult {

    private SportEvent event;

    private List<Outcome> outcomes;
}
