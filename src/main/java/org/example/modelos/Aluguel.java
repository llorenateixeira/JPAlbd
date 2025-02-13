package org.example.modelos;


import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "ALUGUEIS")
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_LOCACAO")
    private org.example.modelos.Locacao locacao;

    @Column(name = "DATA_VENCIMENTO")
    private Date dataVencimento;

    @Column(name = "VALOR_PAGO")
    private Double valorPago;

    @Column(name = "DATA_PAGAMENTO")
    private Date dataPagamento;

    @Column(name = "OBS")
    private String obs;

    // Getters e setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
