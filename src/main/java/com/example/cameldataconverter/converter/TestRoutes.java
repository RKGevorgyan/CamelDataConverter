package com.example.cameldataconverter.converter;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TestRoutes extends EndpointRouteBuilder {

    @Override
    public void configure() throws Exception {
        from(direct("test-1"))
                .log("test-1: ${body}");

        from(direct("test-2"))
                .log("test-2: ${body}");
    }
}
