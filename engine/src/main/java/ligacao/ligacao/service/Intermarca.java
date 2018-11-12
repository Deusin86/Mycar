package ligacao.ligacao.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import ligacao.ligacao.model.Marca;

public interface Intermarca extends MongoRepository<Marca, String>{

}
