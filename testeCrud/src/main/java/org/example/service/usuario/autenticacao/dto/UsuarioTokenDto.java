package org.example.service.usuario.autenticacao.dto;

import school.sptech.exemplojwt.domain.usuario.Login;

public class UsuarioTokenDto {

  private Long userId;
  private Login login;
  private String token;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Login getLogin() {
    return login;
  }

  public void setLogin(Login login) {
    this.login = login;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
