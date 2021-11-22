package br.ufscar.dc.dsw.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "consultas")
public class Consulta{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String cpfCliente;
    private String cpfProfissional;
    private String data;
    private int horario;

    public Consulta() {

    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getCpfProfissional() {
        return cpfProfissional;
    }

    public void setCpfProfissional(String cpfProfissional) {
        this.cpfProfissional = cpfProfissional;
    }

    public void setData(String data) { 
        this.data = data; 
    }
    public String getData() { 
        return this.data; 
    }

    public int getHorario() {
        return horario;
    }
    public void setHorario(int horario) {
        this.horario = horario;
    }

}
