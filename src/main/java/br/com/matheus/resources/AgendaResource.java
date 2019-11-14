package br.com.matheus.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import br.com.matheus.domain.Agenda;
import br.com.matheus.repository.AgendaRepository;

@RestController
public class AgendaResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AgendaRepository agendaRepository;

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/saveAgenda", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveAgenda(@RequestBody Agenda agenda) {

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

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getAgendaByUserId/{clienteId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Agenda> getAgendaByUserId(@PathVariable("clienteId") Integer clienteId) {

		logger.info("Resgatando agenda do cliente ID: " + clienteId);

		try {

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			List<Agenda> agendas = agendaRepository.findByClienteIdOrderByData(clienteId);
			agendas.stream().forEach(x -> {

				String a = formatter.format(x.getData());
				
				try {
					x.setData(formatter.parse(a));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});

			return agendas;

		} catch (Exception e) {
			logger.info("Ocorreu erro ao recuperar agenda. " + e);
			return new ArrayList<Agenda>();
		}

	}
	
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getAgendaByAnuncianteId/{clienteId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Agenda> getAgendaByAnuncianteId(@PathVariable("clienteId") Integer clienteId) {

		logger.info("Resgatando agenda do cliente ID: " + clienteId);

		try {

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			List<Agenda> agendas = agendaRepository.findByAnuncianteIdOrderByData(clienteId);
			agendas.stream().forEach(x -> {

				String a = formatter.format(x.getData());
				
				try {
					x.setData(formatter.parse(a));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});

			return agendas;

		} catch (Exception e) {
			logger.info("Ocorreu erro ao recuperar agenda. " + e);
			return new ArrayList<Agenda>();
		}

	}
	
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/finalizarAnuncio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> finalizarAnuncio(@RequestBody String id) {

		logger.info("Finalizando compromisso na agenda ID: " + id);

		try {

			Agenda agenda = agendaRepository.findById(Integer.parseInt(id));
			agenda.setIsActive(false);
			agendaRepository.save(agenda);

			logger.info("Encerramos seu compromisso com sucesso. Agradecemos por utilizar nossos serviços.");
			return new ResponseEntity<>(
					new Gson().toJson(
							"Encerramos seu compromisso com sucesso. Agradecemos por utilizar nossos serviços."),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.info("Ocorreu erro ao finalizar compromisso na agenda. " + e);
			return new ResponseEntity<>(new Gson().toJson("Ocorreu erro ao finalizar compromisso na agenda. " + e),
					HttpStatus.OK);
		}

	}

}
