package org.example.modelos;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ALUGUEIS")
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_LOCACAO", nullable = false)
    private Locacao locacao;

    @Column(name = "DATA_VENCIMENTO", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "VALOR_PAGO")
    private Double valorPago;

    @Column(name = "DATA_PAGAMENTO")
    private LocalDate dataPagamento;

    @Column(name = "OBS")
    private String obs;

    // 🔹 Construtor padrão
    public Aluguel() {
    }

    // 🔹 Getters e Setters

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

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getLocacaoId() {
        return (locacao != null) ? locacao.getId() : null;
    }
}
