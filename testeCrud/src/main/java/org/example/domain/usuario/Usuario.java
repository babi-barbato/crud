package org.example.domain.usuario;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "users")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_name")
  private String nome;

  @Column(name = "user_cellphone")
  private Long telefone;

  @Column(name = "about")
  private String sobre;

  @Column(name = "user_stars")
  private Double estrelas;

  @Column(name = "user_cpf")
  private String cpf;

  @Column(name = "user_date_birthday")
  private LocalDate dtNasc;

  @Column(name = "user_activit_amount")
  private Integer acitivitAmount;

  @Column(name = "user_type")
  private String tipo;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "login_id")
  private Login login;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_address_fk")
  private Endereco endereco;

  public Login getLogin() {return login;}
  public void setLogin(Login login) {this.login = login;}

  public Endereco getEndereco() {return endereco;}
  public void setEndereco(Endereco endereco) {this.endereco = endereco;}

  public Long getTelefone() {return telefone;}
  public void setTelefone(Long telefone) {this.telefone = telefone;}

  public String getSobre() {return sobre;}
  public void setSobre(String sobre) {this.sobre = sobre;}

  public Double getEstrelas() {return estrelas;}
  public void setEstrelas(Double estrelas) {this.estrelas = estrelas;}

  public String getCpf() {return cpf;}
  public void setCpf(String cpf) {this.cpf = cpf;}

  public LocalDate getDtNasc() {return dtNasc;}
  public void setDtNasc(LocalDate dtNasc) {this.dtNasc = dtNasc;}

  public Integer getAcitivitAmount() {return acitivitAmount;}
  public void setAcitivitAmount(Integer acitivitAmount) {this.acitivitAmount = acitivitAmount;}

  public String getTipo() {return tipo;}
  public void setTipo(String tipo) {this.tipo = tipo;}

  public Login getLoginId() {return login;}

  public void setLoginId(Login loginId) {this.login = loginId;}

  public Long getId() {return id;}
  public void setId(Long id) {this.id = id;}

  public String getNome() {return nome;}
  public void setNome(String nome) {this.nome = nome;}

}