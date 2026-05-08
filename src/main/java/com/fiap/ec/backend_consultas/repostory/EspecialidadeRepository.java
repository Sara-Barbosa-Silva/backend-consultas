package com.fiap.ec.backend_consultas.repostory;
import com.fiap.ec.backend_consultas.model.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
}