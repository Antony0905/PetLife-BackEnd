package br.com.matheus.resources;

import java.util.Date;

import javax.validation.Valid;

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

import br.com.matheus.domain.Agenda;
import br.com.matheus.repository.AgendaRepository;

@RestController
public class AgendaResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AgendaRepository agendaRepository;

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/saveAgenda", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveAgenda(@Valid @RequestBody Agenda agenda) {

		logger.info("Cadastrando nova Agenda: " + agenda);

		try {

			agenda.setIsActive(true);
			agenda.setDataCadastro(new Date());
			agendaRepository.save(agenda);

			logger.info("Serviço agendado com sucesso. Aguarde o passeador no seu endereço na data escolhida.");
			return new ResponseEntity<>(
					new Gson().toJson(
							"Serviço agendado com sucesso. Aguarde o passeador no seu endereço na data escolhida."),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.info("Ocorreu erro ao cadastrar anúncio. " + e);
			return new ResponseEntity<>(new Gson().toJson("Ocorreu erro ao cadastrar Agenda. " + e), HttpStatus.OK);
		}

	}

}
