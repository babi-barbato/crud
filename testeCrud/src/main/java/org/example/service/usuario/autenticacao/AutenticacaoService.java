package org.example.service.usuario.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.exemplojwt.domain.usuario.Login;
import school.sptech.exemplojwt.domain.usuario.Usuario;
import school.sptech.exemplojwt.domain.usuario.repository.LoginRepository;
import school.sptech.exemplojwt.domain.usuario.repository.UsuarioRepository;
import school.sptech.exemplojwt.service.usuario.autenticacao.dto.UsuarioDetalhesDto;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private LoginRepository loginRepository;

  // Método da interface implementada
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//    Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(username);
    System.out.println(username);
    Optional<Login> usuarioOpt = loginRepository.findByEmail(username);

    if (usuarioOpt.isEmpty()) {

      throw new UsernameNotFoundException(String.format("usuario: %s nao encontrado", username));
    }

    Usuario usuarioAutenticado =
            usuarioRepository.findByLogin(usuarioOpt.get())
                    .orElseThrow(
                            () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                    );

    return new UsuarioDetalhesDto(usuarioAutenticado);
  }
}
