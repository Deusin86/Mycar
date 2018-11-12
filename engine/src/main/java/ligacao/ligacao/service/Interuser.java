package ligacao.ligacao.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import ligacao.ligacao.model.User;


public interface Interuser extends MongoRepository<User, String>{

	
	
	// Login findBy_id(ObjectId _id);
	//public List<Login> listar_login();
}
