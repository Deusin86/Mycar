package ligacao.ligacao.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import ligacao.ligacao.model.Revisao;

public interface Interrevisoes extends MongoRepository<Revisao, String>{

}
