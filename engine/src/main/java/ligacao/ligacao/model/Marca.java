package ligacao.ligacao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Marca")
public class Marca {

	
	@Id
	public String id;
	
	public String logo, nome, username;

	public Marca() {
		super();
	}

	public Marca(String id, String logo, String nome, String username) {
		super();
		this.id = id;
		this.logo = logo;
		this.nome = nome;
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
}
