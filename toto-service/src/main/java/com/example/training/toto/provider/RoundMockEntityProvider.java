package com.example.training.toto.provider;

import com.example.training.common.provider.CachedEntityProvider;
import com.example.training.common.reader.CsvReader;
import com.example.training.toto.domain.Round;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
public class RoundMockEntityProvider extends CachedEntityProvider<LocalDate, Round> {

    private static final String TOTO_DATA = "toto.csv";

    private final CsvReader reader;

    @Override
    protected Map<LocalDate, Round> initCache() {
        return reader.getData(TOTO_DATA, Round.class)
                .stream()
                .collect(toMap(Round::getDate, x -> x));
    }
}
