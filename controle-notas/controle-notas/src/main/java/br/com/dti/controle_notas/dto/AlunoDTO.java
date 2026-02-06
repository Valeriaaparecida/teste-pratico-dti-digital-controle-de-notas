package br.com.dti.controle_notas.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class AlunoDTO {
    @NotBlank
    private String nome;

    @Min(value = 0, message = "Frequência deve ser no mínimo 0%")
    @Max(value = 100, message = "Frequência deve ser no máximo 100%")
    private Double frequencia;

    private List<
            @Min(value = 0, message = "Nota inválida (mínimo 0)")
            @Max(value = 10, message = "Nota inválida (máximo 10)")
                    Double
            > notas;

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
