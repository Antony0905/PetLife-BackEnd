package br.com.matheus.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheus.domain.Anuncio;
import br.com.matheus.repository.AnuncioRepository;

@Service
public class AnuncioService {

	@Autowired
	private AnuncioRepository anuncioRepository;

	public void save(Anuncio anuncio) throws ParseException {

		anuncioRepository.save(anuncio);

	}

	public List<Anuncio> findAll() throws ParseException {

		return anuncioRepository.findAll();

	}

}
