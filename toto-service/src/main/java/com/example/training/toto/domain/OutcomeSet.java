package com.example.training.toto.domain;

import lombok.Builder;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Value
@Builder
@Embeddable
public class OutcomeSet {

    @Column(name = "O1", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Outcome o1;

    @Column(name = "O2", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Outcome o2;

    @Column(name = "O3", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Outcome o3;

    @Column(name = "O4", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Outcome o4;

    @Column(name = "O5", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Outcome o5;

    @Column(name = "O6", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Outcome o6;

    @Column(name = "O7", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Outcome o7;

    @Column(name = "O8", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Outcome o8;

    @Column(name = "O9", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Outcome o9;

    @Column(name = "O10", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Outcome o10;

    @Column(name = "O11", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Outcome o11;

    @Column(name = "O12", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Outcome o12;

    @Column(name = "O13", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Outcome o13;

    @Column(name = "O14", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Outcome o14;

    private OutcomeSet() {
        this.o1 = null;
        this.o2 = null;
        this.o3 = null;
        this.o4 = null;
        this.o5 = null;
        this.o6 = null;
        this.o7 = null;
        this.o8 = null;
        this.o9 = null;
        this.o10 = null;
        this.o11 = null;
        this.o12 = null;
        this.o13 = null;
        this.o14 = null;
    }

    private OutcomeSet(Outcome o1, Outcome o2,
               Outcome o3, Outcome o4,
               Outcome o5, Outcome o6,
               Outcome o7, Outcome o8,
               Outcome o9, Outcome o10,
               Outcome o11, Outcome o12,
               Outcome o13, Outcome o14) {
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
        this.o4 = o4;
        this.o5 = o5;
        this.o6 = o6;
        this.o7 = o7;
        this.o8 = o8;
        this.o9 = o9;
        this.o10 = o10;
        this.o11 = o11;
        this.o12 = o12;
        this.o13 = o13;
        this.o14 = o14;
    }
}
