package com.example.training.exchangeservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.apache.http.entity.ContentType;

import java.util.Map;

@Builder
@Getter
public class RequestEntity {

    private String httpMethod;

    private String path;

    private String body;

    private ContentType contentType;

    @Singular("headerParam")
    private Map<String, String> headerParams;

    @Singular("queryParam")
    private Map<String, String> queryParams;

    @Singular("pathParam")
    private Map<String, String> pathParams;
}
