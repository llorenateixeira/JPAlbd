package org.example.modelos;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "LOCACAO")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_IMOVEL", nullable = false)
    private Imovel imovel;

    @ManyToOne
    @JoinColumn(name = "ID_INQUILINO", nullable = false)
    private Cliente inquilino;

    @Column(name = "VALOR_ALUGUEL", nullable = false)
    private Double valorAluguel;

    @Column(name = "DIA_VENCIMENTO", nullable = false)
    private Integer diaVencimento;

    @Column(name = "DATA_INICIO", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "DATA_FIM", nullable = false)
    private LocalDate dataFim;

    // 🔹 Construtor padrão
    public Locacao() {
    }

    // 🔹 Getters e Setters

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

    public Integer getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(Integer diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getIdImovel() {
        return (imovel != null) ? imovel.getId() : null;
    }

    public Integer getIdInquilino() {
        return (inquilino != null) ? inquilino.getId() : null;
    }

    public void setAtivo(boolean b) {
    }
}
