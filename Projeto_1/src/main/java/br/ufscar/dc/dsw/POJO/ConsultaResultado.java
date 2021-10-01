package br.ufscar.dc.dsw.POJO;

public class ConsultaResultado {
    
    private String nome;
    private String data;
    
    public ConsultaResultado(String nome, String data) {
        this.nome = nome;
        this.data = data;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}
