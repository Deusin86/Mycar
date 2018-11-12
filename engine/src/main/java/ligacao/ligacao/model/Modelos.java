package ligacao.ligacao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="Modelos")
public class Modelos {
	
	@Id
	public String id;
	 public String imagem_modelo,nome_marca, nome_modelo,username,
     nr_portas,combustivel,consumo,potencia,matricula,motor;
	  
	public Modelos() {
		super();
	}

	public Modelos(String id, String imagem_modelo, String nome_marca, String nome_modelo, String username,
			String nr_portas, String combustivel, String consumo, String potencia, String matricula, String motor) {
		super();
		this.id = id;
		this.imagem_modelo = imagem_modelo;
		this.nome_marca = nome_marca;
		this.nome_modelo = nome_modelo;
		this.username = username;
		this.nr_portas = nr_portas;
		this.combustivel = combustivel;
		this.consumo = consumo;
		this.potencia = potencia;
		this.matricula = matricula;
		this.motor = motor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImagem_modelo() {
		return imagem_modelo;
	}

	public void setImagem_modelo(String imagem_modelo) {
		this.imagem_modelo = imagem_modelo;
	}

	public String getNome_marca() {
		return nome_marca;
	}

	public void setNome_marca(String nome_marca) {
		this.nome_marca = nome_marca;
	}

	public String getNome_modelo() {
		return nome_modelo;
	}

	public void setNome_modelo(String nome_modelo) {
		this.nome_modelo = nome_modelo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNr_portas() {
		return nr_portas;
	}

	public void setNr_portas(String nr_portas) {
		this.nr_portas = nr_portas;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public String getConsumo() {
		return consumo;
	}

	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}

	public String getPotencia() {
		return potencia;
	}

	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	
	
	 
}
