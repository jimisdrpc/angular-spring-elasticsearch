package com.poc.search.repository;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.poc.search.model.Correntista;

import java.util.List;

@Repository
public interface CorrentistaRepository extends ElasticsearchRepository<Correntista, Long> {
	List<Correntista> findAll();

	List<Correntista> search(QueryBuilder query);
}
