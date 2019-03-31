import com.example.training.common.guice.Guicified;
import com.example.training.common.guice.annotation.WithModules;
import com.example.training.toto.domain.Price;
import com.example.training.toto.domain.Round;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import static org.junit.Assert.assertEquals;

@WithModules(TotoTestModule.class)
public class CsvToRoundListMapperTest implements Guicified {

    private static final String TEST_FILE_NAME = "test.csv";
    private static final Currency TEST_CURRENCY = Currency.getInstance("UAH");

    private CsvMapper csvMapper;
    private CsvSchema csvSchema;

    @Before
    public void setup() {
        final Injector injector = getInjector();
        this.csvMapper = injector.getInstance(CsvMapper.class);
        this.csvSchema = injector.getInstance(CsvSchema.class);
    }

    @Test
    public void shouldMapCsvRowToRound() throws IOException {
        try (InputStream csvFile = getClass().getClassLoader().getResourceAsStream(TEST_FILE_NAME)) {
            MappingIterator<Round> it = csvMapper.readerFor(Round.class)
                    .with(csvSchema)
                    .readValues(csvFile);
            List<Round> rounds = it.readAll();

            assertEquals(1, rounds.size());
            Round round = rounds.get(0);
            assertEquals(2004, round.getYear());
            assertEquals(44, round.getWeek());
            assertEquals(1, round.getNumOfGames14Hits());
            assertEquals(new Price(BigDecimal.valueOf(6_056_019L), TEST_CURRENCY), round.getPriceOf14Hits());
            assertEquals(1, round.getNumOfGames13Hits());
            assertEquals(new Price(BigDecimal.valueOf(1_730_291L), TEST_CURRENCY), round.getPriceOf13Hits());
            assertEquals(61, round.getNumOfGames12Hits());
            assertEquals(new Price(BigDecimal.valueOf(42_548L), TEST_CURRENCY), round.getPriceOf12Hits());
            assertEquals(911, round.getNumOfGames11Hits());
            assertEquals(new Price(BigDecimal.valueOf(2_849L), TEST_CURRENCY), round.getPriceOf11Hits());
            assertEquals(7837, round.getNumOfGames10Hits());
            assertEquals(new Price(BigDecimal.valueOf(552L), TEST_CURRENCY), round.getPriceOf10Hits());
            assertEquals(TotoTestUtils.toOutcomeSet("2", "1", "2", "X", "1", "2", "1", "1", "1", "1", "2", "X", "1", "1"), round.getOutcomes());
        }
    }
}
