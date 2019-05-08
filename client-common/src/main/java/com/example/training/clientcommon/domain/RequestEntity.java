package com.example.training.clientcommon.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.apache.http.entity.ContentType;

import java.util.Map;

@Getter
@Builder(builderClassName = "Builder")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestEntity {

    private final String httpMethod;

    private final String path;

    private final String body;

    private final ContentType contentType;

    @Singular("headerParam")
    private final Map<String, String> headerParams;

    @Singular("queryParam")
    private final Map<String, String> queryParams;

    @Singular("pathParam")
    private final Map<String, String> pathParams;
}
