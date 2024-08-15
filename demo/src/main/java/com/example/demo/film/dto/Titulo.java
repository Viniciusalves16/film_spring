package com.example.demo.film.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Titulo  {

    @SerializedName("Title")
    private String nome;
    private int anoDeLancamento;
    private boolean incluidoNoPlano;
    private double somaDasAvaliacoes;
    private int totalDeAvaliacoes;
    private int duracaoEmMinutos;



}
