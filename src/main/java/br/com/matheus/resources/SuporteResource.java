package br.com.matheus.resources;

import java.util.Date;

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
import br.com.matheus.domain.Suporte;
import br.com.matheus.domain.Usuario;
import br.com.matheus.dto.ServiceDTO;
import br.com.matheus.repository.AgendaRepository;
import br.com.matheus.repository.SuporteRepository;
import br.com.matheus.service.UsuarioService;

@RestController
public class SuporteResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SuporteRepository suporteRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AgendaRepository agendaRepository;

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/relatarProblema", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> finalizarAnuncio(@RequestBody ServiceDTO service) {

		logger.info("Cadastrando problema");

		try {

			Suporte suporte = new Suporte();

			suporte.setAgendaId(Integer.parseInt(service.getAgendaId()));
			suporte.setAnuncianteId(Integer.parseInt(service.getAnuncianteId()));
			suporte.setDescricao(service.getComentario());

			Agenda agenda = agendaRepository.findById(Integer.parseInt(service.getAgendaId()));
			Usuario user = usuarioService.findUserById(agenda.getClienteId());
			Usuario anunciante = usuarioService.findUserById(agenda.getAnuncianteId());

			suporte.setAnuncianteEmail(anunciante.getEmail());
			suporte.setUserEmail(user.getEmail());
			suporte.setUserId(user.getId());

			suporte.setDataCadastro(new Date());

			suporteRepository.save(suporte);

			logger.info("Problema registrado com sucesso. Agradecemos por utilizar nossos serviços.");
			return new ResponseEntity<>(
					new Gson().toJson("Problema registrado com sucesso. Agradecemos por utilizar nossos serviços."),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.info("Ocorreu erro ao finalizar registrar problema. " + e);
			return new ResponseEntity<>(new Gson().toJson("Ocorreu erro ao finalizar registrar problema. " + e),
					HttpStatus.OK);
		}

	}

}
