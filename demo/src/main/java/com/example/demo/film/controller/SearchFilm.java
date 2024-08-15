package com.example.demo.film.controller;


import com.example.demo.film.dto.Titulo;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class SearchFilm {

    @Value("${OMDB_API_URL}")
    private String baseUrl;

    @Value("${OMDB_API_KEY}")
    private String apiKey;

    @GetMapping("films/{name}")
    public ResponseEntity searchFilms(@Valid @PathVariable(value = "name") String name) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl+"?t=" + name+"&apiKey=" + apiKey))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return ResponseEntity.ok(new Gson().fromJson(response.body(), Titulo.class));
    }

}
