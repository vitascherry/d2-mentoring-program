package com.example.training.common.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Log4j2
@AllArgsConstructor
public class CsvReader {

    private final CsvMapper csvMapper;
    private final CsvSchema csvSchema;

    public <T> List<T> getData(String fileName, Class<T> entityType) {
        return read(fileName, csvMapper.readerFor(entityType));
    }

    public <T> List<T> getData(String fileName, JavaType javaType) {
        return read(fileName, csvMapper.readerFor(javaType));
    }

    public <T> List<T> getData(String fileName, TypeReference<T> typeReference) {
        return read(fileName, csvMapper.readerFor(typeReference));
    }

    private <T> List<T> read(@NonNull String fileName, @NonNull ObjectReader reader) {
        try (InputStream csvFile = getClass().getClassLoader().getResourceAsStream(fileName)) {
            MappingIterator<T> it = reader
                    .with(csvSchema)
                    .readValues(csvFile);
            return it.readAll();
        } catch (IOException e) {
            log.error("Error occurred while reading from file {}. ErrorMassage: ", fileName, e);
            throw new RuntimeException(e);
        }
    }
}
