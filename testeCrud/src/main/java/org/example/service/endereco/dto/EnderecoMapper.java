package org.example.service.endereco.dto;

import school.sptech.exemplojwt.domain.usuario.Endereco;

public class EnderecoMapper {

    public static Endereco of(EnderecoCriacaoDto enderecoCriacaoDto){
        Endereco endereco =  new Endereco();
        endereco.setTipoRua(enderecoCriacaoDto.getTipoRua());
        endereco.setNomeRua(enderecoCriacaoDto.getNomeRua());
        endereco.setNumeroResidedncia(enderecoCriacaoDto.getNumeroResidedncia());
        endereco.setComplemento(enderecoCriacaoDto.getComplemento());
        endereco.setBairro(enderecoCriacaoDto.getBairro());
        endereco.setCep(enderecoCriacaoDto.getCep());
        endereco.setCidade(enderecoCriacaoDto.getCidade());
        endereco.setEstado(enderecoCriacaoDto.getEstado());
        return endereco;
    }
}
