package com.example.training.clientcommon.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PathFormatter {

    private String pattern;

    private final Map<String, String> queryParams = new HashMap<>();

    private final Map<String, String> pathParams = new HashMap<>();

    public PathFormatter withPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public PathFormatter withQueryParams(Map<String, String> queryParams) {
        if (queryParams != null) this.queryParams.putAll(queryParams);
        return this;
    }

    public PathFormatter withPathParams(Map<String, String> pathParams) {
        if (pathParams != null) this.pathParams.putAll(pathParams);
        return this;
    }

    public String format() {
        final StringBuilder url = new StringBuilder(pattern);

        pathParams.forEach((key, value) -> {
            String populated = String.format("{%s}", key);
            int start = url.indexOf(populated);
            int end = start + populated.length();
            url.replace(start, end, value);
        });

        if (!queryParams.isEmpty()) {
            url.append('?');
        }
        for (Iterator<Map.Entry<String, String>> it = queryParams.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, String> param = it.next();
            if (param.getKey() != null && !param.getKey().isEmpty()) {
                url.append(param.getKey()).append('=');
            }
            url.append(param.getValue());
            if (it.hasNext()) {
                url.append('&');
            }
        }

        return url.toString();
    }
}
