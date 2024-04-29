package org.example.service.usuario.dto;

import school.sptech.exemplojwt.domain.usuario.Endereco;
import school.sptech.exemplojwt.domain.usuario.Login;
import school.sptech.exemplojwt.domain.usuario.Usuario;
import school.sptech.exemplojwt.service.endereco.dto.EnderecoMapper;
import school.sptech.exemplojwt.service.usuario.autenticacao.dto.UsuarioTokenDto;


public class UsuarioMapper {

  public static UsuarioViewDto of(Usuario usuario, Login login, Endereco endereco){
    UsuarioViewDto usuarioViewDto = new UsuarioViewDto();

    usuarioViewDto.setNome(usuario.getNome());
    usuarioViewDto.setTelefone(usuario.getTelefone());
    usuarioViewDto.setSobre(usuario.getSobre());
    usuarioViewDto.setEstrelas(usuario.getEstrelas());
    usuarioViewDto.setCpf(usuario.getCpf());
    usuarioViewDto.setDtNasc(usuario.getDtNasc());
    usuarioViewDto.setAcitivitAmount(usuario.getAcitivitAmount());
    usuarioViewDto.setTipo(usuario.getTipo());
    usuarioViewDto.setEmail(login.getEmail());
    usuarioViewDto.setSenha(login.getSenha());
    usuarioViewDto.setTipoRua(endereco.getTipoRua());
    usuarioViewDto.setNomeRua(endereco.getNomeRua());
    usuarioViewDto.setNumeroResidedncia(endereco.getNumeroResidedncia());
    usuarioViewDto.setComplemento(endereco.getComplemento());
    usuarioViewDto.setBairro(endereco.getBairro());
    usuarioViewDto.setCep(endereco.getCep());
    usuarioViewDto.setCidade(endereco.getCidade());
    usuarioViewDto.setEstado(endereco.getEstado());

    return usuarioViewDto;
  }

  public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
    Usuario usuario = new Usuario();

    usuario.setLoginId(usuarioCriacaoDto.getLogin());
    usuario.setEndereco(EnderecoMapper.of(usuarioCriacaoDto.getEnderecoCriacaoDto()));
    usuario.setNome(usuarioCriacaoDto.getNome());
    usuario.setTelefone(usuarioCriacaoDto.getTelefone());
    usuario.setSobre(usuarioCriacaoDto.getSobre());
    usuario.setEstrelas(usuarioCriacaoDto.getEstrelas());
    usuario.setCpf(usuarioCriacaoDto.getCpf());
    usuario.setDtNasc(usuarioCriacaoDto.getDtNasc());
    usuario.setAcitivitAmount(usuarioCriacaoDto.getAcitivitAmount());
    usuario.setTipo(usuarioCriacaoDto.getTipo());

    return usuario;
  }

  public static UsuarioTokenDto of(Usuario usuario, String token) {
    UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

    Login l = new Login();
    l.setSenha(usuario.getLoginId().getSenha());
    l.setEmail(usuario.getLoginId().getEmail());

    usuarioTokenDto.setUserId(usuario.getId());
    usuarioTokenDto.setLogin(l);
    usuarioTokenDto.setToken(token);

    return usuarioTokenDto;
  }
}
