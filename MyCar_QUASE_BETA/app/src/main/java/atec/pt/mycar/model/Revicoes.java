package atec.pt.mycar.model;


import java.util.Date;

public class Revicoes {

    String id,
            matricula, nome_marca,
            nome_modelo, username,
            custos, km,
            agua, oleo,
            filtro_combustivel, filtro_ar,
            filtro_ac, luzes,
            pneus, travoes,
            alinhamento, eletrica,
            descricao_prob, ano, mes,
            dia,imagem;

    public Revicoes() {
    }

    public Revicoes(String id, String matricula, String nome_marca, String nome_modelo, String username, String custos, String km, String agua, String oleo, String filtro_combustivel, String filtro_ar, String filtro_ac, String luzes, String pneus, String travoes, String alinhamento, String eletrica, String descricao_prob, String ano, String mes, String dia, String imagem) {
        this.id = id;
        this.matricula = matricula;
        this.nome_marca = nome_marca;
        this.nome_modelo = nome_modelo;
        this.username = username;
        this.custos = custos;
        this.km = km;
        this.agua = agua;
        this.oleo = oleo;
        this.filtro_combustivel = filtro_combustivel;
        this.filtro_ar = filtro_ar;
        this.filtro_ac = filtro_ac;
        this.luzes = luzes;
        this.pneus = pneus;
        this.travoes = travoes;
        this.alinhamento = alinhamento;
        this.eletrica = eletrica;
        this.descricao_prob = descricao_prob;
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
        this.imagem = imagem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public String getCustos() {
        return custos;
    }

    public void setCustos(String custos) {
        this.custos = custos;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getAgua() {
        return agua;
    }

    public void setAgua(String agua) {
        this.agua = agua;
    }

    public String getOleo() {
        return oleo;
    }

    public void setOleo(String oleo) {
        this.oleo = oleo;
    }

    public String getFiltro_combustivel() {
        return filtro_combustivel;
    }

    public void setFiltro_combustivel(String filtro_combustivel) {
        this.filtro_combustivel = filtro_combustivel;
    }

    public String getFiltro_ar() {
        return filtro_ar;
    }

    public void setFiltro_ar(String filtro_ar) {
        this.filtro_ar = filtro_ar;
    }

    public String getFiltro_ac() {
        return filtro_ac;
    }

    public void setFiltro_ac(String filtro_ac) {
        this.filtro_ac = filtro_ac;
    }

    public String getLuzes() {
        return luzes;
    }

    public void setLuzes(String luzes) {
        this.luzes = luzes;
    }

    public String getPneus() {
        return pneus;
    }

    public void setPneus(String pneus) {
        this.pneus = pneus;
    }

    public String getTravoes() {
        return travoes;
    }

    public void setTravoes(String travoes) {
        this.travoes = travoes;
    }

    public String getAlinhamento() {
        return alinhamento;
    }

    public void setAlinhamento(String alinhamento) {
        this.alinhamento = alinhamento;
    }

    public String getEletrica() {
        return eletrica;
    }

    public void setEletrica(String eletrica) {
        this.eletrica = eletrica;
    }

    public String getDescricao_prob() {
        return descricao_prob;
    }

    public void setDescricao_prob(String descricao_prob) {
        this.descricao_prob = descricao_prob;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}