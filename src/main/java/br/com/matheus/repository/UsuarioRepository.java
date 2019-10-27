package br.com.matheus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheus.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findFirstByEmailAndPassword(String usuario, String password);

	Usuario findFirstByEmail(String email);

	Usuario findFirstById(Integer userId);

}
