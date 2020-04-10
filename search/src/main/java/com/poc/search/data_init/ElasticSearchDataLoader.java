package com.poc.search.data_init;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.poc.search.model.Correntista;
import com.poc.search.service.CorrentistaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ElasticSearchDataLoader implements CommandLineRunner {

    @Value("classpath:data/correntistas.json")
    private Resource usersJsonFile;

    @Autowired
    private CorrentistaService correntistaService;

    @Override
    public void run(String... args) throws Exception {
        if (this.isInitialized()) {
            return;
        }

        List<Correntista> users = this.loadUsersFromFile();
        users.forEach(correntistaService::save);
    }

    private List<Correntista> loadUsersFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, CorrentistaInitData.class);
        List<CorrentistaInitData> allFakeUsers = objectMapper.readValue(this.usersJsonFile.getFile(), collectionType);
        return allFakeUsers.stream().map(this::from).map(this::generateId).collect(Collectors.toList());
    }

    private Correntista generateId(Correntista correntista) {
        correntista.setId(UUID.randomUUID().toString());
        return correntista;
    }

	private Correntista from(CorrentistaInitData correntistaJson) {
        Correntista correntista = new Correntista();
        correntista.setConta(correntistaJson.getConta());
        correntista.setSobrenome(correntistaJson.getSobrenome());
        correntista.setNome(correntistaJson.getNome());
        return correntista;
    }

    private boolean isInitialized() {
        return this.correntistaService.count() > 0;
    }
}
