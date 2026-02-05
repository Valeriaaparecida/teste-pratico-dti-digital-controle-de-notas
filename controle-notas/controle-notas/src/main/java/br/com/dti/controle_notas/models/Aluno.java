package br.com.dti.controle_notas.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "alunos")
@Entity(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ElementCollection
    private List<Double> notas = new ArrayList<>();

    private Double frequencia;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }

    public Double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Double frequencia) {
        this.frequencia = frequencia;
    }

    public void adicionarNota(Double nota) {
        if (notas.size() < 5) {
            notas.add(nota);
        }
    }

    public Double getMedia() {
        if (notas == null || notas.isEmpty()) return 0.0;

        double soma = 0;

        for (Double nota : notas) {
            soma += nota;
        }

        return soma / notas.size();
    }
}
