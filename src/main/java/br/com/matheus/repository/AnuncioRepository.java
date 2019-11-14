package br.com.matheus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheus.domain.Anuncio;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {

	List<Anuncio> findAllByOrderByDataCadastroDesc();

	List<Anuncio> findByUserIdOrderByIdDesc(Integer userId);

}
