package com.fatec.loja;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
public class ProdutoController {
    @Autowired
    ProdutoRepository bd;
    LojaService service;

    // gravar produto
    @PostMapping("/api/produto")
    public void gravar(@RequestBody Produto obj){
        bd.save(obj);
        System.out.println("Produto gravado com sucesso!");
    }
    // procurar produto
    @GetMapping("/api/produto/{codigo}")
    public Produto carregar(@PathVariable("codigo") int c){
        if(bd.existsById(c)){
            return bd.findById(c).get();    
        } else {
            return new Produto();
        }
    }
    // alterar dados
    @PutMapping("/api/produto")
    public void alterar(@RequestBody Produto obj){
        if(bd.existsById(obj.getCodigo())){
            bd.save(obj);
            System.out.println("Produto alterado com sucesso!");
        }
    }
    // deletar
    @DeleteMapping("/api/produto/{codigo}")
    public void remover(@PathVariable("codigo") int codigo){
        if(bd.existsById(codigo)){
            bd.deleteById(codigo);
            System.out.println("Produto removido com sucesso!");
        }
    }
    // listar todos os produtos
    @GetMapping("/api/produtos")
    public List<Produto> listar(){ 
        return bd.findAll();
    }

    @GetMapping("/api/produtos/vitrine")
    public List<Produto> carregarVitrine(){
        return bd.listarVitrine();
    }
    
    @GetMapping("/api/produtos/busca/{termo}")
    public List<Produto> realizarBusca(@RequestParam String termo) {
        return bd.fazerBusca(termo);
    }

    // Testar email
    @PatchMapping("/api/produto/testeEmail")
    public void enviarEmail(){
        String msg = service.enviarEmail("nomeusuario@email.com", "teste de envio de email setembro", "boa noite pessoal");
        System.out.println(msg);
    }
}
