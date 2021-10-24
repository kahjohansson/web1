package br.ufscar.dc.dsw.domain;

public class Profissional extends Usuario {
    
    private String area;
    private String especialidade;
    private String curriculo;
    
    public Profissional(String cpf, String nome, String email, String senha, String area, String especialidade,
            String curriculo) {
        super(cpf, nome, email, senha);
        this.area = area;
        this.especialidade = especialidade;
        this.curriculo = curriculo;
    }

    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    public String getCurriculo() {
        return curriculo;
    }
    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

}
