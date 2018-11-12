package ligacao.ligacao.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import ligacao.ligacao.model.Modelos;

public interface Intermodelos extends MongoRepository<Modelos, String>{
	
	

}
