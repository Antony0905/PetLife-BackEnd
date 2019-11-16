package br.com.matheus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheus.domain.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

	List<Comentario> findByUserIdOrderByDataCadastro(Integer userId);

}
