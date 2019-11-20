package br.com.matheus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheus.domain.PetImage;

@Repository
public interface PetImageRepository extends JpaRepository<PetImage, Integer> {

	PetImage findByPetId(Integer petId);

}
