package org.example.domain.usuario;

import jakarta.persistence.*;

@Entity
@Table(name = "user_address")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer id;

    @Column(name = "address_street_type")
    private String tipoRua;

    @Column(name = "address_street_name")
    private String nomeRua;

    @Column(name = "address_residence_number")
    private Integer numeroResidedncia;

    @Column(name = "address_complement")
    private String complemento;

    @Column(name = "address_neighbornhood")
    private String bairro;

    @Column(name = "address_cep")
    private String cep;

    @Column(name = "address_locality_name")
    private String cidade;

    @Column(name = "address_federation_sign")
    private String estado;



    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

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
