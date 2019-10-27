package br.com.matheus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheus.domain.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {


}
