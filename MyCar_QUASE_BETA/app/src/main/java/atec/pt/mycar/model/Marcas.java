package atec.pt.mycar.model;

public class Marcas {

    String id,logo,nome, username;

    public Marcas() {
    }

    public Marcas(String id, String logo, String nome, String username) {
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
