package br.com.dti.controle_notas.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
public class AlunoDTO {
    @NotBlank
    private String nome;

    @Min(0)
    @Max(100)
    private Double frequencia;

    private List<@Min(0) @Max(10) Double> notas;

    public String getNome() {
        return nome;
    }

    public Double getFrequencia() {
        return frequencia;
    }

    public List<Double> getNotas() {
        return notas;
    }
}
