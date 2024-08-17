package com.example.demo.film.dto;

import com.example.demo.film.exception.ErrorCustomizeYearException;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Titulo  {

    private String nome;
    private int anoDeLancamento;
    private boolean incluidoNoPlano;
    private double somaDasAvaliacoes;
    private int totalDeAvaliacoes;
    private int duracaoEmMinutos;


    public Titulo(TituloOmdb title) {
        this.nome = title.title();

        if (title.year().length() >4){
            throw new ErrorCustomizeYearException("Não foi possível converter o ano por possuir mais que 4 caracteres");
        }
        this.anoDeLancamento = Integer.valueOf(title.year());
        this.duracaoEmMinutos = Integer.valueOf(title.runtime().substring(0,2));
    }
}
