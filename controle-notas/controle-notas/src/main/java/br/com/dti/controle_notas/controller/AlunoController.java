package br.com.dti.controle_notas.controller;

import br.com.dti.controle_notas.dto.AlunoDTO;
import br.com.dti.controle_notas.models.Aluno;
import br.com.dti.controle_notas.repository.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    public Aluno criarAluno(@RequestBody @Valid AlunoDTO dto)
    {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setFrequencia(dto.getFrequencia());
        aluno.setNotas(dto.getNotas());

        return alunoRepository.save(aluno);
    }

    @GetMapping
    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    @GetMapping("/acima-media")
    public List<Aluno> alunosAcimaMedia() {
        List<Aluno> alunos = alunoRepository.findAll();

        double mediaTurma = alunos.stream()
                .mapToDouble(Aluno::getMedia)
                .average()
                .orElse(0);

        return alunos.stream()
                .filter(a -> a.getMedia() > mediaTurma)
                .toList();
    }
    @GetMapping("/frequencia-baixa")
    public List<Aluno> frequenciaBaixa() {
        return alunoRepository.findAll()
                .stream()
                .filter(a -> a.getFrequencia() < 75)
                .toList();
    }

    @GetMapping("/media-turma")
    public List<Double> mediaTurmaPorDisciplina() {

        List<Aluno> alunos = alunoRepository.findAll();

        if (alunos.isEmpty()) return List.of();

        int qtdDisciplinas = alunos.get(0).getNotas().size();

        List<Double> medias = new ArrayList<>();

        for (int i = 0; i < qtdDisciplinas; i++) {
            int index = i;

            double media = alunos.stream()
                    .mapToDouble(a -> a.getNotas().get(index))
                    .average()
                    .orElse(0);

            medias.add(media);
        }

        return medias;
    }

}
