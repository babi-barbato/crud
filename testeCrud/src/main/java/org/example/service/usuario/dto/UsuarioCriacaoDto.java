package org.example.service.usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.exemplojwt.domain.usuario.Login;
import school.sptech.exemplojwt.service.endereco.dto.EnderecoCriacaoDto;

import java.time.LocalDate;
import java.util.Date;

public class UsuarioCriacaoDto {

  @Size(min = 3)
  @Schema(description = "Nome do usuário", example = "Reis")
  private String nome;

  @NotNull
  private Long telefone;

  @NotBlank@Size(min = 3)
  @NotBlank
  private String sobre;

  @Size(min = 3)
  @NotBlank
  private String cpf;

  @NotNull
  private LocalDate dtNasc;

  @Size(min = 1,max = 1)
  @NotBlank
  private String tipo;

  private Integer acitivitAmount;
  private Double estrelas;

  private Login login;
  private EnderecoCriacaoDto enderecoCriacaoDto;


  public EnderecoCriacaoDto getEnderecoCriacaoDto() {
    return enderecoCriacaoDto;
  }

  public void setEnderecoCriacaoDto(EnderecoCriacaoDto enderecoCriacaoDto) {
    this.enderecoCriacaoDto = enderecoCriacaoDto;
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

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public Integer getAcitivitAmount() {
    return acitivitAmount;
  }

  public void setAcitivitAmount(Integer acitivitAmount) {
    this.acitivitAmount = acitivitAmount;
  }

  public Double getEstrelas() {
    return estrelas;
  }

  public void setEstrelas(Double estrelas) {
    this.estrelas = estrelas;
  }

  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  public Login getLogin() {
    return login;
  }
  public void setLogin(Login login) {
    this.login = login;
  }
}
