package ligacao.ligacao.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {
	
	@Id
	public String id;
	
	public String nome,contacto,email,idade,username, password;
	
	

	public User() {
		super();
	}



	public User(String id, String nome, String contacto, String email, String idade, String username, String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.contacto = contacto;
		this.email = email;
		this.idade = idade;
		this.username = username;
		this.password = password;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getContacto() {
		return contacto;
	}



	public void setContacto(String contacto) {
		this.contacto = contacto;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getIdade() {
		return idade;
	}



	public void setIdade(String idade) {
		this.idade = idade;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



}
