package br.com.matheus.resources;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.matheus.domain.Anuncio;
import br.com.matheus.service.AnuncioService;

@RestController
public class AnuncioResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AnuncioService anuncioService;

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/novoAnuncio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> novoAnuncio(@Valid @RequestBody Anuncio anuncio) {

		logger.info("Cadastrando novo anúncio: " + anuncio);

		try {

			anuncioService.save(anuncio);

			logger.info("Cadastro de anúncio realizado com sucesso");
			return new ResponseEntity<>(new Gson().toJson("Cadastro de anúncio realizado com sucesso"), HttpStatus.OK);

		} catch (Exception e) {
			logger.info("Ocorreu erro ao cadastrar anúncio. " + e);
			return new ResponseEntity<>(new Gson().toJson("Ocorreu erro ao cadastrar anúncio. " + e), HttpStatus.OK);
		}

	}

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getAnuncios", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Anuncio> getAnuncios() {

		logger.info("Resgatando todos os anuncios!");

		try {

			return anuncioService.findAll();

		} catch (Exception e) {
			logger.info("Ocorreu erro ao recuperar anúncios. " + e);
			return new ArrayList<Anuncio>();
		}

	}

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getAnunciosByUserId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Anuncio> getAnunciosByUserId(@PathVariable("userId") Integer userId) {

		logger.info("Resgatando todos os anuncios do userId: " + userId);

		try {

			if (userId == null) {
				return new ArrayList<Anuncio>();
			}

			return anuncioService.findByUserId(userId);

		} catch (Exception e) {
			logger.info("Ocorreu erro ao recuperar anúncios. " + e);
			return new ArrayList<Anuncio>();
		}

	}

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/deleteAnuncio/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteAnuncio(@PathVariable("id") Integer id) {

		logger.info("Excluindo anuncio id: " + id);

		try {

			Anuncio anuncio = new Anuncio();
			anuncio.setId(id);

			anuncioService.delete(anuncio);
			return new ResponseEntity<>(new Gson().toJson("Anuncio ID " + id + " excluído com sucesso!"),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.info("Ocorreu erro ao excluir o anúncio id. " + id + " ERROR: " + e);
			return new ResponseEntity<>(new Gson().toJson("Ocorreu erro ao excluir anuncio ID " + id + " ERROR: " + e),
					HttpStatus.BAD_REQUEST);

		}

	}

}
