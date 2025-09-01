package com.fatec.loja;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Cliente {
    @Id
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private int ativo;
    private String endereco;
    private String cidade;
    private String cep;
    private String logradouro;
    private String complemento;
    
}
