package org.example.service.endereco.dto;

import jakarta.validation.constraints.*;

public class EnderecoCriacaoDto {

    @NotBlank
    @Size(min = 3)
    private String tipoRua;

    @NotBlank
    @Size(min = 3)
    private String nomeRua;

    @NotNull
    @Positive
    private Integer numeroResidedncia;

    private String complemento;

    @NotBlank
    @Size(min = 3)
    private String bairro;

    @NotBlank
    @Size(min = 3)
    private String cep;

    @NotBlank
    @Size(min = 3)
    private String cidade;

    @NotBlank
    @Size(min = 3)
    private String estado;

    public String getTipoRua() {return tipoRua;}
    public void setTipoRua(String tipoRua) {this.tipoRua = tipoRua;}

    public String getNomeRua() {return nomeRua;}
    public void setNomeRua(String nomeRua) {this.nomeRua = nomeRua;}

    public Integer getNumeroResidedncia() {return numeroResidedncia;}
    public void setNumeroResidedncia(Integer numeroResidedncia) {this.numeroResidedncia = numeroResidedncia;}

    public String getComplemento() {return complemento;}
    public void setComplemento(String complemento) {this.complemento = complemento;}

    public String getBairro() {return bairro;}
    public void setBairro(String bairro) {this.bairro = bairro;}

    public String getCep() {return cep;}
    public void setCep(String cep) {this.cep = cep;}

    public String getCidade() {return cidade;}
    public void setCidade(String cidade) {this.cidade = cidade;}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}
}
