package com.example.training.sportsbetting.provider;

import com.example.training.common.provider.CachedEntityProvider;
import com.example.training.common.reader.CsvReader;
import com.example.training.sportsbetting.domain.Outcome;
import com.example.training.sportsbetting.domain.identifier.DecimalDomainEntityIdentifier;
import com.example.training.sportsbetting.domain.identifier.DomainEntityIdentifier;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@AllArgsConstructor
public class MockSportEventResultsProvider extends CachedEntityProvider<DomainEntityIdentifier, List<Outcome>> {

    private static final String FILE_NAME = "csv/sport-events-results.csv";

    private final CsvReader csvReader;

    @Override
    protected Map<DomainEntityIdentifier, List<Outcome>> initCache() {
        List<Outcome> sportEventResults = csvReader.getData(FILE_NAME, Outcome.class);

        return sportEventResults.stream()
                .collect(groupingBy(outcome -> new DecimalDomainEntityIdentifier(outcome.getIdentifier().getEventId()), toList()));
    }
}
