package br.com.matheus.resources;

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

import br.com.matheus.domain.UserImage;
import br.com.matheus.repository.UserImageRepository;

@RestController
public class ImageProfileResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserImageRepository userImageRepository;

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/saveUserImage", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveUserImage(@RequestBody UserImage userImage) {

		logger.info("Salvando nova image UserId: " + userImage.getUserId());

		try {

			userImageRepository.save(userImage);

			logger.info("Imagem salva com sucesso");
			return new ResponseEntity<>(new Gson().toJson("Imagem salva com sucesso"), HttpStatus.OK);

		} catch (Exception e) {
			logger.info("Ocorreu erro ao salvar imagem. " + e);
			return new ResponseEntity<>(new Gson().toJson("Ocorreu erro ao salvar imagem. " + e), HttpStatus.OK);
		}

	}

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getUserImageByUserId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserImage getUserImageByUserId(@PathVariable("userId") Integer userId) {

		logger.info("Resgatando imagem do userId: " + userId);

		try {

			UserImage userImage = userImageRepository.findByUserId(userId);

			if (userImage == null) {
				userImage = new UserImage();
				return userImage;
			}

			return userImage;

		} catch (Exception e) {
			logger.info("Ocorreu erro ao recuperar imagem. " + e);
			throw e;
		}

	}

}
