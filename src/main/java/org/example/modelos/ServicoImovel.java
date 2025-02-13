package org.example.modelos;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "SERVICOS_IMOVEL")
public class ServicoImovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_PROFISSIONAL")
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "ID_IMOVEL")
    private Imovel imovel;

    @Column(name = "DATA_SERVICO")
    private Date dataServico;

    @Column(name = "VALOR_TOTAL")
    private Double valorTotal;

    @Column(name = "OBS")
    private String obs;

    // Getters e setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
