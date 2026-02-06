package br.com.dti.controle_notas.controller;

import br.com.dti.controle_notas.models.Aluno;
import br.com.dti.controle_notas.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    public Aluno criarAluno(@RequestBody Aluno aluno) {
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
}
