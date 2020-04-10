package com.poc.search.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "correntistas")
@Setting(settingPath = "/resources/data/es-config/elastic-setting.json")
@Getter
@Setter
public class Correntista {
	@Id
	private String id;
	private String conta;
	private String sobrenome;

	@Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
	private String nome;
}
