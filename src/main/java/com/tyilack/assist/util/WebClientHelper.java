package com.tyilack.assist.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author giantan
 */
public class WebClientHelper {

    public static <T> T get(String url, Class<T> tClass) {
        Mono<T> resp = WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(tClass);
        return resp.block();
    }

    public static String get(String url) {
        Mono<String> resp = WebClient.create()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);

        return resp.block();
    }

    public static <T> T post(String url, Map<String, String> params, Class<T> tClass) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> entry: params.entrySet()) {
            formData.add(entry.getKey(), entry.getValue());
        }
        Mono<T> resp = WebClient.create().post()
                .uri(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve().bodyToMono(tClass);

        return resp.block();
    }


    public static String post(String url, Map<String, String> params) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> entry: params.entrySet()) {
            formData.add(entry.getKey(), entry.getValue());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);;
        Mono<String> resp = WebClient.create().post()
                .uri(url)
                .header("")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromFormData(formData))
                .retrieve().bodyToMono(String.class);

        return resp.block();
    }

    public static <T> T postJson(String url, Object jsonBody, Class<T> tClass) {
        Mono<T> resp = WebClient.create().post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(jsonBody), Object.class)
                .retrieve().bodyToMono(tClass);

        return resp.block();
    }

    public static String postJson(String url, Object jsonBody) {
        Mono<String> resp = WebClient.create().post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(jsonBody), Object.class)
                .retrieve().bodyToMono(String.class);

        return resp.block();
    }

    public static <T> T postRawJson(String url, String jsonBody, Class<T> tClass) {
        Mono<T> resp = WebClient.create().post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(jsonBody))
                .retrieve().bodyToMono(tClass);

        return resp.block();
    }

    public static String postRawJson(String url, String jsonBody) {
        Mono<String> resp = WebClient.create().post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(jsonBody))
                .retrieve().bodyToMono(String.class);

        return resp.block();
    }

}
