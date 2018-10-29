package com.example.training.toto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BetResult {

    private List<Hit> hits;

    private Price price;
}
