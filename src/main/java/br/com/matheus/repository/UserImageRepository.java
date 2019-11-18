package br.com.matheus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheus.domain.UserImage;

@Repository
public interface UserImageRepository extends JpaRepository<UserImage, Integer> {

	UserImage findByUserId(Integer userId);

}
