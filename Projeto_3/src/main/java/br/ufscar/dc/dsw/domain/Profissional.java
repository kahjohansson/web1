package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "profissionais")
public class Profissional extends Usuario {
    

    @NotBlank (message = "{NotBlank.area.curriculo}")
	@Size(min = 3, max = 60)
    @Column(nullable = false, unique = true, length = 256)
    private String area;

    @NotBlank (message = "{NotBlank.profissional.especialidade}")
	@Size(min = 3, max = 60)
    @Column(nullable = false, unique = true, length = 256)
    private String especialidade;

    @NotBlank (message = "{NotBlank.profissional.curriculo}")
	@Size(min = 3, max = 60)
    @Column(nullable = false, unique = true, length = 512)
    private String curriculo;
    
    public Profissional() {
		super();
	}

    public Profissional(String cpf, String nome, String email, String senha, String papel, String area, String especialidade,
            String curriculo) {
        super(cpf, nome, email, senha, papel);
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
