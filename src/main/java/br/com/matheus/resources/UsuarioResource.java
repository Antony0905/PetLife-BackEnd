package br.com.matheus.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.matheus.domain.Usuario;
import br.com.matheus.dto.UsuarioDTO;
import br.com.matheus.service.UsuarioService;

@RestController
public class UsuarioResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsuarioService usuarioService;

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@RequestBody UsuarioDTO user) {

		logger.info("Registrando novo usuário! " + user.getNome());

		try {

			String messages = user.validarUsuarioDTO(user);

			if (!"".equals(messages)) {
				return new ResponseEntity<>(new Gson().toJson(messages), HttpStatus.BAD_REQUEST);
			}

			user.setEmail(user.getEmail().toUpperCase());
			user.setNome(user.getNome().toUpperCase());
			Usuario usuario = usuarioService.findFirstByEmail(user.getEmail());

			if (usuario == null) {
				usuarioService.save(user);

				logger.info("Registro realizado com sucesso. Bem vindo" + user.getNome());
				return new ResponseEntity<>(
						new Gson().toJson("Registro realizado com sucesso. Bem vindo  " + user.getNome()),
						HttpStatus.OK);
			} else {
				logger.info("Email " + user.getEmail() + " Já cadastrado!");
				return new ResponseEntity<>(new Gson().toJson("Email " + user.getEmail() + " Já cadastrado!"),
						HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			logger.info("Ocorreu erro ao registrar. " + e);
			return new ResponseEntity<>(new Gson().toJson("Ocorreu erro ao registrar. " + e), HttpStatus.OK);
		}

	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody UsuarioDTO user) {

		logger.info("Atualizando usuario! " + user.getNome());

		try {

			String messages = user.validarUsuarioDTO(user);

			if (!"".equals(messages)) {
				return new ResponseEntity<>(new Gson().toJson(messages), HttpStatus.BAD_REQUEST);
			}

			user.setEmail(user.getEmail().toUpperCase());
			user.setNome(user.getNome().toUpperCase());

			usuarioService.save(user);

			logger.info("Registro realizado com sucesso. Bem vindo" + user.getNome());
			return new ResponseEntity<>(new Gson().toJson("Usuario atualizado com sucesso. UserName:" + user.getNome()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.info("Ocorreu erro ao atualizar usuario. " + e);
			return new ResponseEntity<>(new Gson().toJson("Ocorreu erro ao atualizar usuario. " + e), HttpStatus.OK);
		}

	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/findUserByEmail", produces = MediaType.APPLICATION_JSON_VALUE)
	public Usuario findUserByEmail(@RequestBody String email) {

		logger.info("Buscando usuário por email: " + email);

		try {

			Usuario usuario = usuarioService.findFirstByEmail(email.toUpperCase());

			if (usuario == null) {

				logger.info("Nenhum usuário encontrado com o email: " + email);
				return new Usuario();
			}

			return usuario;

		} catch (Exception e) {
			logger.info("Ocorreu erro ao buscar usuário pelo email: . " + email + e);
			return new Usuario();
		}

	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/findUserById", produces = MediaType.APPLICATION_JSON_VALUE)
	public Usuario findUserById(@RequestBody Integer userId) {

		logger.info("Buscando usuário por Id: " + userId);

		try {

			Usuario usuario = usuarioService.findUserById(userId);

			if (usuario == null) {

				logger.info("Nenhum usuário encontrado com o Id: " + userId);
				return new Usuario();
			}

			return usuario;

		} catch (Exception e) {
			logger.info("Ocorreu erro ao buscar usuário pelo Id: . " + userId + e);
			return new Usuario();
		}

	}
}
