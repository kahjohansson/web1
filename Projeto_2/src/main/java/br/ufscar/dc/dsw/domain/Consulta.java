package br.ufscar.dc.dsw.domain;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "consultas")
public class Consulta{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cpfCliente")
    // private String cpfCliente;
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "cpfProfissional")
    // private String cpfProfissional;
    private Profissional profissional;

    // @Temporal(TemporalType.TIMESTAMP)
    private String data;

    private int horario;

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Profissional getProfissional() {
        return profissional;
    }
    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
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
