package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ProdutoController {
    /*
     * POST - Inserir
     * GET - LER
     * PUT - Alterar
     * Delete - Apagar
     */
    @GetMapping("/api/produto/{codigo}")
    public Produto carregar(@PathVariable("codigo") int codigo){
        // Buscar BD
        Produto obj = new Produto();
        obj.setCodigo(codigo);
        obj.setNome("Marreta 2KG");
        obj.setDescritivo("Marreta boa top de mais");
        obj.setDestaque(1);
        obj.setKeywords("Marreta");
        obj.setPromo(40);
        obj.setValor(50);
        obj.setQuantidade(1);
        return obj;
    }
}
