import com.example.training.toto.domain.Outcome;
import com.example.training.toto.domain.OutcomeSet;
import com.example.training.toto.domain.Price;
import com.example.training.toto.domain.Round;
import com.example.training.toto.mapper.OutcomeDeserializer;
import com.example.training.toto.mapper.PriceDeserializer;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import static com.example.training.toto.domain.Outcome.fromValue;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType.NUMBER;
import static com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType.STRING;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.Assert.assertEquals;

public class CsvToRoundListMapperTest {

    private static final String TEST_FILE_NAME = "test.csv";

    private ObjectReader csvReader;
    private DecimalFormat decimalFormatter;

    @Before
    public void setup() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setMonetaryDecimalSeparator('.');
        symbols.setGroupingSeparator(' ');

        decimalFormatter = new DecimalFormat("###,###.##", symbols);
        decimalFormatter.setRoundingMode(HALF_EVEN);
        decimalFormatter.setParseBigDecimal(true);

        csvReader = new CsvMapper()
                .registerModule(new JavaTimeModule())
                .registerModule(new SimpleModule()
                        .addDeserializer(Price.class, new PriceDeserializer(decimalFormatter))
                        .addDeserializer(Outcome.class, new OutcomeDeserializer()))
                .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(WRITE_DATES_AS_TIMESTAMPS)
                .readerFor(Round.class)
                .with(CsvSchema.builder()
                        .setColumnSeparator(';')
                        .addColumn("year", NUMBER)
                        .addColumn("week", NUMBER)
                        .addColumn("round", NUMBER)
                        .addColumn("date", STRING)
                        .addColumn("numOfGames14Hits", NUMBER)
                        .addColumn("priceOf14Hits", STRING)
                        .addColumn("numOfGames13Hits", NUMBER)
                        .addColumn("priceOf13Hits", STRING)
                        .addColumn("numOfGames12Hits", NUMBER)
                        .addColumn("priceOf12Hits", STRING)
                        .addColumn("numOfGames11Hits", NUMBER)
                        .addColumn("priceOf11Hits", STRING)
                        .addColumn("numOfGames10Hits", NUMBER)
                        .addColumn("priceOf10Hits", STRING)
                        .addColumn("o1", STRING)
                        .addColumn("o2", STRING)
                        .addColumn("o3", STRING)
                        .addColumn("o4", STRING)
                        .addColumn("o5", STRING)
                        .addColumn("o6", STRING)
                        .addColumn("o7", STRING)
                        .addColumn("o8", STRING)
                        .addColumn("o9", STRING)
                        .addColumn("o10", STRING)
                        .addColumn("o11", STRING)
                        .addColumn("o12", STRING)
                        .addColumn("o13", STRING)
                        .addColumn("o14", STRING)
                        .setLineSeparator(System.getProperty("line.separator"))
                        .setNullValue("-")
                        .build());
    }

    @Test
    public void shouldMapCsvRowToRound() throws IOException {
        try (InputStream file = getClass().getClassLoader().getResourceAsStream(TEST_FILE_NAME)) {
            MappingIterator<Round> it = csvReader.readValues(file);
            List<Round> rounds = it.readAll();

            assertEquals(1, rounds.size());
            Round round = rounds.get(0);
            assertEquals(2004, round.getYear());
            assertEquals(44, round.getWeek());
            assertEquals(1, round.getNumOfGames14Hits());
            assertEquals(new Price(BigDecimal.valueOf(6_056_019L), decimalFormatter.getCurrency()), round.getPriceOf14Hits());
            assertEquals(1, round.getNumOfGames13Hits());
            assertEquals(new Price(BigDecimal.valueOf(1_730_291L), decimalFormatter.getCurrency()), round.getPriceOf13Hits());
            assertEquals(61, round.getNumOfGames12Hits());
            assertEquals(new Price(BigDecimal.valueOf(42_548L), decimalFormatter.getCurrency()), round.getPriceOf12Hits());
            assertEquals(911, round.getNumOfGames11Hits());
            assertEquals(new Price(BigDecimal.valueOf(2_849L), decimalFormatter.getCurrency()), round.getPriceOf11Hits());
            assertEquals(7837, round.getNumOfGames10Hits());
            assertEquals(new Price(BigDecimal.valueOf(552L), decimalFormatter.getCurrency()), round.getPriceOf10Hits());
            assertEquals(toOutcomeSet("2", "1", "2", "X", "1", "2", "1", "1", "1", "1", "2", "X", "1", "1"), round.getOutcomes());
        }
    }

    private OutcomeSet toOutcomeSet(String o1, String o2, String o3, String o4, String o5, String o6, String o7,
                                    String o8, String o9, String o10, String o11, String o12, String o13, String o14) {
        return new OutcomeSet(new Outcome[]{
                fromValue(o1), fromValue(o2),
                fromValue(o3), fromValue(o4),
                fromValue(o5), fromValue(o6),
                fromValue(o7), fromValue(o8),
                fromValue(o9), fromValue(o10),
                fromValue(o11), fromValue(o12),
                fromValue(o13), fromValue(o14)
        });
    }
}
