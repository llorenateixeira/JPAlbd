package org.example.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "IMOVEIS")
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_PROPRIETARIO", nullable = false)
    private Cliente proprietario;

    @Column(name = "LOGRADOURO", nullable = false)
    private String logradouro;

    @Column(name = "BAIRRO", nullable = false)
    private String bairro;

    @Column(name = "CEP", nullable = false)
    private String cep;

    @Column(name = "METRAGEM")
    private Integer metragem;

    @Column(name = "DORMITORIOS")
    private Integer dormitorios;

    @Column(name = "BANHEIROS")
    private Integer banheiros;

    @Column(name = "SUITES")
    private Integer suites;

    @Column(name = "VAGAS_GARAGEM")
    private Integer vagasGaragem;

    @Column(name = "VALOR_ALUGUEL_SUGERIDO", nullable = false)
    private Double valorAluguelSugerido;

    @Column(name = "STATUS", nullable = false)
    private String status = "DISPONIVEL"; // Valor padrão

    // 🔹 Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getProprietario() {
        return proprietario;
    }

    public void setProprietario(Cliente proprietario) {
        this.proprietario = proprietario;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getMetragem() {
        return metragem;
    }

    public void setMetragem(Integer metragem) {
        this.metragem = metragem;
    }

    public Integer getDormitorios() {
        return dormitorios;
    }

    public void setDormitorios(Integer dormitorios) {
        this.dormitorios = dormitorios;
    }

    public Integer getBanheiros() {
        return banheiros;
    }

    public void setBanheiros(Integer banheiros) {
        this.banheiros = banheiros;
    }

    public Integer getSuites() {
        return suites;
    }

    public void setSuites(Integer suites) {
        this.suites = suites;
    }

    public Integer getVagasGaragem() {
        return vagasGaragem;
    }

    public void setVagasGaragem(Integer vagasGaragem) {
        this.vagasGaragem = vagasGaragem;
    }

    public Double getValorAluguelSugerido() {
        return valorAluguelSugerido;
    }

    public void setValorAluguelSugerido(Double valorAluguelSugerido) {
        this.valorAluguelSugerido = valorAluguelSugerido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if ("ALUGADO".equalsIgnoreCase(status) || "DISPONIVEL".equalsIgnoreCase(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status inválido. Use 'ALUGADO' ou 'DISPONIVEL'.");
        }
    }

    // 🔹 Método para ALUGAR o imóvel
    public void alugar() {
        if ("DISPONIVEL".equalsIgnoreCase(this.status)) {
            this.status = "ALUGADO";
        } else {
            throw new IllegalStateException("Imóvel já está alugado!");
        }
    }

    // 🔹 Método para LIBERAR o imóvel
    public void liberar() {
        if ("ALUGADO".equalsIgnoreCase(this.status)) {
            this.status = "DISPONIVEL";
        } else {
            throw new IllegalStateException("O imóvel já está disponível!");
        }
    }
}
