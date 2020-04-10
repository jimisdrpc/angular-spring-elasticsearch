package com.poc.search.service;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.search.model.Correntista;
import com.poc.search.repository.CorrentistaRepository;

import java.util.List;

@Service
public class CorrentistaService {

	@Autowired
	private CorrentistaRepository correntistaRepository;

	public List<Correntista> listAll() {
		return this.correntistaRepository.findAll();
	}

	public Correntista save(Correntista correntista) {
		return this.correntistaRepository.save(correntista);
	}

	public long count() {
		return this.correntistaRepository.count();
	}

	public List<Correntista> search(String palavras) {
		MatchQueryBuilder searchByPalavras = QueryBuilders.matchQuery("nome", palavras);
		return this.correntistaRepository.search(searchByPalavras);
	}
}
