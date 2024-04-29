package org.example.api.controller.usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.exemplojwt.domain.usuario.Usuario;
import school.sptech.exemplojwt.service.usuario.UsuarioService;
import school.sptech.exemplojwt.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.exemplojwt.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.exemplojwt.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.exemplojwt.service.usuario.dto.UsuarioViewDto;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Retorna todos os usuários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna realizado com sucesso.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "204", description = "Não existe nenhum usuário cadastrado.",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<UsuarioViewDto>> listar(){
        return usuarioService.listar();
    }

    @Operation(summary = "Cadastra um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados de criacao incorretos.",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Usuario nao logado.",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {
        this.usuarioService.criar(usuarioCriacaoDto);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Loga um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "401", description = "Usuario passou credenciais incorretas.",
                    content = @Content)})
    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        System.out.println(usuarioLoginDto);
        UsuarioTokenDto usuarioTokenDto = this.usuarioService.autenticar(usuarioLoginDto);

        return ResponseEntity.status(200).body(usuarioTokenDto);
    }

    @Operation(summary = "Deleta um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        return usuarioService.deletar(id);
    }

    @Operation(summary = "Altera informações de um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações alteradas com sucesso.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioViewDto> update(@RequestBody @Valid UsuarioCriacaoDto user, @PathVariable Integer id){
        return usuarioService.update(user, id);
    }

    @Operation(summary = "Altera informações do login de um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações alteradas com sucesso.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",
                    content = @Content) })
    @PutMapping("/login/{id}")
    public ResponseEntity<UsuarioViewDto> updateLogin(@RequestBody @Valid UsuarioLoginDto user, @PathVariable Integer id){
        return usuarioService.updateLogin(user, id);
    }
}