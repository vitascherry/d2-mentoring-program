package com.example.training.toto.service.impl;

import com.example.training.toto.domain.Distribution;
import com.example.training.toto.domain.Outcome;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static com.example.training.toto.domain.Outcome.DRAW;
import static com.example.training.toto.domain.Outcome.FIRST;
import static com.example.training.toto.domain.Outcome.SECOND;
import static java.util.function.Function.identity;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static java.util.stream.Collector.Characteristics.UNORDERED;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DistributionCollector implements Collector<List<Outcome>, List<Distribution>, List<Distribution>> {

    public static DistributionCollector toDistributions() {
        return new DistributionCollector();
    }

    @Override
    public Supplier<List<Distribution>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Distribution>, List<Outcome>> accumulator() {
        return (distributions, outcomes) -> {
            Distribution item = new Distribution();
            item.setFirst(((double) outcomes.stream().filter(FIRST::equals).count()) / outcomes.size());
            item.setSecond(((double) outcomes.stream().filter(SECOND::equals).count()) / outcomes.size());
            item.setDraw(((double) outcomes.stream().filter(DRAW::equals).count()) / outcomes.size());
            distributions.add(item);
        };
    }

    @Override
    public BinaryOperator<List<Distribution>> combiner() {
        return (distributions, distributions2) -> {
            distributions.addAll(distributions2);
            return distributions;
        };
    }

    @Override
    public Function<List<Distribution>, List<Distribution>> finisher() {
        return identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(IDENTITY_FINISH, UNORDERED);
    }
}
