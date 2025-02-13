package org.example.modelos;


import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "LOCACAO")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_IMOVEL")
    private Imovel imovel;

    @ManyToOne
    @JoinColumn(name = "ID_INQUILINO")
    private Cliente inquilino;

    @Column(name = "VALOR_ALUGUEL")
    private Double valorAluguel;

    @Column(name = "PERCENTUAL_MULTA")
    private Double percentualMulta;

    @Column(name = "DIA_VENCIMENTO")
    private Integer diaVencimento;

    @Column(name = "DATA_INICIO")
    private Date dataInicio;

    @Column(name = "DATA_FIM")
    private Date dataFim;

    @Column(name = "ATIVO")
    private Boolean ativo;

    // Getters e setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Cliente getInquilino() {
        return inquilino;
    }

    public void setInquilino(Cliente inquilino) {
        this.inquilino = inquilino;
    }

    public Double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(Double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public Double getPercentualMulta() {
        return percentualMulta;
    }

    public void setPercentualMulta(Double percentualMulta) {
        this.percentualMulta = percentualMulta;
    }

    public Integer getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(Integer diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
