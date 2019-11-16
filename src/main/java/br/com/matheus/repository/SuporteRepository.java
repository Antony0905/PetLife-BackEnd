package br.com.matheus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheus.domain.Suporte;

@Repository
public interface SuporteRepository extends JpaRepository<Suporte, Integer> {

}
