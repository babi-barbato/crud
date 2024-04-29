package org.example.service.usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.exemplojwt.api.configuration.security.jwt.GerenciadorTokenJwt;
import school.sptech.exemplojwt.domain.usuario.Endereco;
import school.sptech.exemplojwt.domain.usuario.Login;
import school.sptech.exemplojwt.domain.usuario.Usuario;
import school.sptech.exemplojwt.domain.usuario.repository.EnderecoRepository;
import school.sptech.exemplojwt.domain.usuario.repository.LoginRepository;
import school.sptech.exemplojwt.domain.usuario.repository.UsuarioRepository;
import school.sptech.exemplojwt.service.endereco.dto.EnderecoMapper;
import school.sptech.exemplojwt.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.exemplojwt.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.exemplojwt.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.exemplojwt.service.usuario.dto.UsuarioMapper;
import school.sptech.exemplojwt.service.usuario.dto.UsuarioViewDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

  private final PasswordEncoder passwordEncoder;
  private final UsuarioRepository usuarioRepository;
  private final LoginRepository loginRepository;
  private final EnderecoRepository enderecoRepository;
  private final GerenciadorTokenJwt gerenciadorTokenJwt;
  private final AuthenticationManager authenticationManager;

  @Autowired
  public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository, LoginRepository loginRepository, EnderecoRepository enderecoRepository,GerenciadorTokenJwt gerenciadorTokenJwt, AuthenticationManager authenticationManager) {
    this.passwordEncoder = passwordEncoder;
    this.usuarioRepository = usuarioRepository;
    this.loginRepository = loginRepository;
    this.enderecoRepository = enderecoRepository;
    this.gerenciadorTokenJwt = gerenciadorTokenJwt;
    this.authenticationManager = authenticationManager;
  }

  // GET LISTA TODOS OS USUARIOS
  public ResponseEntity<List<UsuarioViewDto>> listar(){
    List<Usuario> lista = usuarioRepository.findAll();

    System.out.println(lista.get(0).getNome());
    System.out.println(lista.get(1).getNome());

    if(lista.isEmpty()){
      return ResponseEntity.noContent().build();
    }

    List<UsuarioViewDto> listas = new ArrayList<>();

    for(Usuario u : lista){
      Endereco endereco = u.getEndereco();
      if (endereco != null) {
        listas.add(UsuarioMapper.of(u, u.getLoginId(), endereco));
      } else {
        System.out.println("Endereco é nulo para o usuário: " + u.getNome());
      }
    }

    return ResponseEntity.ok(listas);
  }

  // POST CRIA UM USUARIO
  public void criar(UsuarioCriacaoDto usuarioCriacaoDto) {
    final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);

    Login l = novoUsuario.getLoginId();

    String senhaCriptografada = passwordEncoder.encode(l.getSenha());
    l.setSenha(senhaCriptografada);

    loginRepository.save(l);

    Endereco endereco = novoUsuario.getEndereco();
    endereco.setTipoRua(usuarioCriacaoDto.getEnderecoCriacaoDto().getTipoRua());
    endereco.setNomeRua(usuarioCriacaoDto.getEnderecoCriacaoDto().getNomeRua());
    endereco.setNumeroResidedncia(usuarioCriacaoDto.getEnderecoCriacaoDto().getNumeroResidedncia());
    endereco.setComplemento(usuarioCriacaoDto.getEnderecoCriacaoDto().getComplemento());
    endereco.setBairro(usuarioCriacaoDto.getEnderecoCriacaoDto().getBairro());
    endereco.setCep(usuarioCriacaoDto.getEnderecoCriacaoDto().getCep());
    endereco.setCidade(usuarioCriacaoDto.getEnderecoCriacaoDto().getCidade());
    endereco.setEstado(usuarioCriacaoDto.getEnderecoCriacaoDto().getEstado());
    enderecoRepository.save(endereco);

    novoUsuario.setLoginId(l);
    novoUsuario.setEndereco(endereco);

    this.usuarioRepository.save(novoUsuario);
  }

  // POST LOGIN
  public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {

    System.out.println(usuarioLoginDto.getEmail());
    System.out.println(usuarioLoginDto.getSenha());

    final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
            usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

    final Authentication authentication = this.authenticationManager.authenticate(credentials);

    System.out.println(usuarioLoginDto.getEmail());
    System.out.println(usuarioLoginDto.getSenha());

    Login loginAutenticado =
            loginRepository.findByEmail(usuarioLoginDto.getEmail())
                    .orElseThrow(
                            () -> new ResponseStatusException(404, "Email nao cadastrado", null)
                    );

    Usuario usuarioAutenticado =
            usuarioRepository.findByLogin(loginAutenticado)
                    .orElseThrow(
                            () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                    );

//    Usuario usuarioAutenticado =
//            usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
//                    .orElseThrow(
//                            () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
//                    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    final String token = gerenciadorTokenJwt.generateToken(authentication);

    return UsuarioMapper.of(usuarioAutenticado, token);
  }

  // DELETE
  public ResponseEntity<Void> deletar(Integer id){
    Optional<Usuario> users = usuarioRepository.findById(id);

    if (users.isEmpty()){
      return ResponseEntity.notFound().build();
    }

    usuarioRepository.delete(users.get());
    return ResponseEntity.noContent().build();
  }

  // UPDATE INFOS USUARIO
  public ResponseEntity<UsuarioViewDto> update(UsuarioCriacaoDto usuarioCriacaoDto,Integer id){
    Optional<Usuario> user = usuarioRepository.findById(id);

    if (user.isEmpty()){
      return ResponseEntity.notFound().build();
    }

    user.get().setNome(usuarioCriacaoDto.getNome());
    user.get().setTelefone(usuarioCriacaoDto.getTelefone());
    user.get().setSobre(usuarioCriacaoDto.getSobre());
    user.get().setEstrelas(usuarioCriacaoDto.getEstrelas());
    user.get().setCpf(usuarioCriacaoDto.getCpf());
    user.get().setDtNasc(usuarioCriacaoDto.getDtNasc());
    user.get().setAcitivitAmount(usuarioCriacaoDto.getAcitivitAmount());
    user.get().setTipo(usuarioCriacaoDto.getTipo());

    usuarioRepository.save(user.get());

    UsuarioViewDto userView = UsuarioMapper.of(user.get(),user.get().getLoginId(),user.get().getEndereco());

    return ResponseEntity.ok(userView);
  }

  // UPDATE EMAIL E LOGIN
  public ResponseEntity<UsuarioViewDto> updateLogin(UsuarioLoginDto usuarioLoginDto,Integer id){
    Optional<Usuario> user = usuarioRepository.findById(id);

    if (user.isEmpty()){
      return ResponseEntity.notFound().build();
    }

    Login login = new Login();

    login.setEmail(usuarioLoginDto.getEmail());
    login.setSenha(usuarioLoginDto.getSenha());
    user.get().setLoginId(login);

    usuarioRepository.save(user.get());

    UsuarioViewDto userView = UsuarioMapper.of(user.get(),user.get().getLoginId(),user.get().getEndereco());

    return ResponseEntity.ok(userView);
  }
}