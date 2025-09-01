package com.fatec.loja;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Produto {
    @Id
    private int codigo;
    private String nome;
    private String descritivo;
    private double valor;
    private double promo;
    private int quantidade;
    private int destaque;
    private String keywords;
    
}
