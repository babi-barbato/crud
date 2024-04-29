package org.example.service.usuario.dto;

import jakarta.persistence.*;
import school.sptech.exemplojwt.domain.usuario.Login;

import java.time.LocalDate;
import java.util.Date;

public class UsuarioViewDto {
    private String nome;
    private Long telefone;
    private String sobre;
    private Double estrelas;
    private String cpf;
    private LocalDate dtNasc;
    private Integer acitivitAmount;
    private String tipo;

    private String email;
    private String senha;

    private String tipoRua;
    private String nomeRua;
    private Integer numeroResidedncia;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;


    public String getTipoRua() {
        return tipoRua;
    }

    public void setTipoRua(String tipoRua) {
        this.tipoRua = tipoRua;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public Integer getNumeroResidedncia() {
        return numeroResidedncia;
    }

    public void setNumeroResidedncia(Integer numeroResidedncia) {
        this.numeroResidedncia = numeroResidedncia;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public Double getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(Double estrelas) {
        this.estrelas = estrelas;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(LocalDate dtNasc) {
        this.dtNasc = dtNasc;
    }

    public Integer getAcitivitAmount() {
        return acitivitAmount;
    }

    public void setAcitivitAmount(Integer acitivitAmount) {
        this.acitivitAmount = acitivitAmount;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
