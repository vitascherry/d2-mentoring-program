package com.example.training.toto.domain;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import java.time.LocalDate;

@Value
@Builder
@Entity
@Table(name = "ROUND_")
@NamedQueries({
        @NamedQuery(name = "Round.findByDate",
                query = "SELECT r FROM Round r WHERE r.date = :date"),
        @NamedQuery(name = "Round.findAll",
                query = "SELECT r FROM Round r")
})
public class Round {

    @Id
    @Column(name = "ID_", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "YEAR_", nullable = false)
    private int year;

    @Column(name = "WEEK_", nullable = false)
    private int week;

    @Column(name = "ROUND_NUM")
    private int round;

    @Column(name = "DATE_", nullable = false)
    private LocalDate date;

    @Column(name = "NUM_OF_GAMES_14_HITS", nullable = false)
    private int numOfGames14Hits;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "PRICE_OF_14_HITS_AMOUNT", nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "PRICE_OF_14_HITS_CURRENCY", nullable = false))
    })
    private Price priceOf14Hits;

    @Column(name = "NUM_OF_GAMES_13_HITS", nullable = false)
    private int numOfGames13Hits;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "PRICE_OF_13_HITS_AMOUNT", nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "PRICE_OF_13_HITS_CURRENCY", nullable = false))
    })
    private Price priceOf13Hits;

    @Column(name = "NUM_OF_GAMES_12_HITS", nullable = false)
    private int numOfGames12Hits;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "PRICE_OF_12_HITS_AMOUNT", nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "PRICE_OF_12_HITS_CURRENCY", nullable = false))
    })
    private Price priceOf12Hits;

    @Column(name = "NUM_OF_GAMES_11_HITS", nullable = false)
    private int numOfGames11Hits;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "PRICE_OF_11_HITS_AMOUNT", nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "PRICE_OF_11_HITS_CURRENCY", nullable = false))
    })
    private Price priceOf11Hits;

    @Column(name = "NUM_OF_GAMES_10_HITS", nullable = false)
    private int numOfGames10Hits;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "PRICE_OF_10_HITS_AMOUNT", nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "PRICE_OF_10_HITS_CURRENCY", nullable = false))
    })
    private Price priceOf10Hits;

    @Embedded
    private OutcomeSet outcomes;

    private Round() {
        this.id = null;
        this.year = 0;
        this.week = 0;
        this.round = 0;
        this.date = null;
        this.numOfGames14Hits = 0;
        this.priceOf14Hits = null;
        this.numOfGames13Hits = 0;
        this.priceOf13Hits = null;
        this.numOfGames12Hits = 0;
        this.priceOf12Hits = null;
        this.numOfGames11Hits = 0;
        this.priceOf11Hits = null;
        this.numOfGames10Hits = 0;
        this.priceOf10Hits = null;
        this.outcomes = null;
    }

    private Round(Long id, int year, int week, int round, LocalDate date,
                  int numOfGames14Hits, Price priceOf14Hits,
                  int numOfGames13Hits, Price priceOf13Hits,
                  int numOfGames12Hits, Price priceOf12Hits,
                  int numOfGames11Hits, Price priceOf11Hits,
                  int numOfGames10Hits, Price priceOf10Hits,
                  OutcomeSet outcomes) {
        this.id = id;
        this.year = year;
        this.week = week;
        this.round = round;
        this.date = date;
        this.numOfGames14Hits = numOfGames14Hits;
        this.priceOf14Hits = priceOf14Hits;
        this.numOfGames13Hits = numOfGames13Hits;
        this.priceOf13Hits = priceOf13Hits;
        this.numOfGames12Hits = numOfGames12Hits;
        this.priceOf12Hits = priceOf12Hits;
        this.numOfGames11Hits = numOfGames11Hits;
        this.priceOf11Hits = priceOf11Hits;
        this.numOfGames10Hits = numOfGames10Hits;
        this.priceOf10Hits = priceOf10Hits;
        this.outcomes = outcomes;
    }
}
