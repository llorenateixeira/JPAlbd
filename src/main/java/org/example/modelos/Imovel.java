package org.example.modelos;


import jakarta.persistence.*;



@Entity
@Table(name = "IMOVEIS")
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_PROPRIETARIO")
    private Cliente proprietario;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_IMOVEL")
    private TipoImovel tipoImovel;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "CEP")
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

    @Column(name = "VALOR_ALUGEL_SUGERIDO")
    private Double valorAluguelSugerido;

    @Column(name = "OBS")
    private String obs;

    // Getters e setters

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

    public TipoImovel getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(TipoImovel tipoImovel) {
        this.tipoImovel = tipoImovel;
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

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
