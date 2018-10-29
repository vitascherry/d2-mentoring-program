package com.example.training.sportsbetting;

import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.sportsbetting.guice.SportsBettingModule;
import com.example.training.sportsbetting.service.SportEventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Injector;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@WithModules(SportsBettingModule.class)
public class App implements Guicified {

    private final SportEventService sportEventService;
    private final ObjectMapper objectMapper;

    private App() {
        final Injector injector = getInjector();
        sportEventService = injector.getInstance(SportEventService.class);
        objectMapper = injector.getInstance(ObjectMapper.class);
    }

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("Welcome, dear Player!");
        System.out.println("It's Sports Betting Game");
        System.out.println("Look, what sport events did we prepare for you!");
        App app = new App();
        System.out.println(app.objectMapper.writeValueAsString(app.sportEventService.getSportEvents()));
        System.out.println("Thank you for using our app!");
    }
}
