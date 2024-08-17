package com.example.demo.film.controller;

import com.example.demo.film.dto.Titulo;
import com.example.demo.film.dto.TituloOmdb;
import com.example.demo.film.exception.ErrorCustomizeYearException;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
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

        Titulo titulo = new Titulo();

        Gson gson = new Gson();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "?t=" + name + "&apiKey=" + apiKey))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //utilizando biblioteca GSON para converter campos que chegam como string em objeto json
//        return ResponseEntity.ok(new Gson().fromJson(response.body(), Titulo.class));
            //GsonBuilder, configura e interpreta o padrão que chega sendo letra em caixa alta ou não

            TituloOmdb title = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create().fromJson(response.body(), TituloOmdb.class);
            titulo = new Titulo(title);

            //escrevendo arquivo depois de converter
            FileWriter arquivo = new FileWriter("filmes.txt");
            arquivo.write(gson.toJson(titulo));
            arquivo.close();
        } catch (ErrorCustomizeYearException e) {
            System.out.println("erro: " + e.getMessage());

        } finally {
            // utilizando o finally é possível evitar duplicação de código
            System.out.println("finally sempre executa o código independente de ser uma exceção ou não");
        }


        // objeto ja tratado

//        return ResponseEntity.ok(title);
        return ResponseEntity.ok(titulo);
    }

}
