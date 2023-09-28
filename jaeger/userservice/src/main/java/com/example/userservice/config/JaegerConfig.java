package com.example.userservice.config;

import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaegerConfig {

//    @Bean
//    public static JaegerTracer jaegerTracer() {
//        io.jaegertracing.Configuration.SamplerConfiguration samplerConfig =
//                io.jaegertracing.Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
//        io.jaegertracing.Configuration.ReporterConfiguration reporterConfig =
//                io.jaegertracing.Configuration.ReporterConfiguration.fromEnv().withLogSpans(true).ge;
//
//        io.jaegertracing.Configuration config = new io.jaegertracing.Configuration("user-service")
//                .withSampler(samplerConfig)
//                .withReporter(reporterConfig);
//        return config.getTracer();
//    }

    @Value("${spring.application.name}")
    String applicationName;

    @Value("${opentracing.jaeger.http-sender.url}")
    String url;

    @Bean
    OtlpHttpSpanExporter otlpHttpSpanExporter() {
        return OtlpHttpSpanExporter.builder()
                .setEndpoint(url)
                .build();
    }

    @Bean
    SdkTracerProvider sdkTracerProvider(){
        return SdkTracerProvider.builder()
                .setResource(Resource.create(Attributes.of(ResourceAttributes.SERVICE_NAME, applicationName)))
                .addSpanProcessor(SimpleSpanProcessor.create(otlpHttpSpanExporter())).build();
    }

    @Bean
    Tracer tracer(){
        return sdkTracerProvider().get(applicationName);
    }
}
