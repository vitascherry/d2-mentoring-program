package com.example.training.toto.mapper;

import com.example.training.toto.domain.Outcome;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.training.toto.domain.Outcome.fromValue;
import static java.util.regex.Pattern.CASE_INSENSITIVE;

public class OutcomeDeserializer extends JsonDeserializer<Outcome> {

    private static final Pattern OUTCOME_PATTERN = Pattern.compile("(?:\\+?)([x12])", CASE_INSENSITIVE);

    @Override
    public Outcome deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
        String text = parser.getText();
        Matcher matcher = OUTCOME_PATTERN.matcher(text);
        if (matcher.find()) {
            String value = matcher.group(1);
            return fromValue(value);
        }

        return null;
    }
}
