package br.com.dti.controle_notas.repository;

import br.com.dti.controle_notas.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository  extends JpaRepository<Aluno, Long> {
}
