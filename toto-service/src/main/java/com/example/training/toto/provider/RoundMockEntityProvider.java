package com.example.training.toto.provider;

import com.example.training.common.mapper.EntityMapper;
import com.example.training.common.provider.CachedEntityProvider;
import com.example.training.common.reader.CsvReader;
import com.example.training.toto.domain.Round;
import com.example.training.toto.dto.RoundDto;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
public class RoundMockEntityProvider extends CachedEntityProvider<LocalDate, Round> {

    private static final String TOTO_DATA = "toto.csv";

    private final CsvReader reader;
    private final EntityMapper<Round, RoundDto> entityMapper;

    @Override
    protected Map<LocalDate, Round> initCache() {
        return reader.getData(TOTO_DATA, RoundDto.class)
                .stream()
                .map(entityMapper::reverse)
                .collect(toMap(round -> round.getDate() != null ?
                                round.getDate() :
                                LocalDate.of(round.getYear(), 1, 1).plusWeeks(round.getWeek()),
                        identity()));
    }
}
