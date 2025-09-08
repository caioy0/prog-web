package com.fatec.loja;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ClienteController {
    @Autowired
    ClienteRepository bd;

    // gravar cliente
    @PostMapping("/api/cliente")
    public void gravar(@RequestBody Cliente obj){
        bd.save(obj);
        System.out.println("Info do cliente gravada com sucesso!");
    }

    // carregar cleinte
    @GetMapping("/api/cliente/{codigo}")
    public Cliente carregar(@PathVariable("codigo") int c){
        return bd.findById(c).orElse(new Cliente());
    }

    // else {return new Cliente};

    // alterar dados
   @PutMapping("/api/cliente")
    public void alterar(@RequestBody Cliente obj){
        if(bd.existsById(obj.getId())){
            bd.save(obj);
            System.out.println("Cliente alterado com sucesso!");
        }
    }

    // deletar
    @DeleteMapping("/api/cliente/{codigo}")
    public void remover(@PathVariable("codigo") int codigo){
        if(bd.existsById(codigo)){
            bd.deleteById(codigo);
            System.out.println("Cliente removido com sucesso!");
        }
    }

    // Listar todos os clientes
    @GetMapping("/api/clientes")
    public List<Cliente> listar(){
        return bd.findAll();
    }

    // Listar clientes inativos
    @GetMapping("/api/cliente/inativos")
    public List<Cliente> carregarInativos(){
        return bd.listarInativos();
    }

    // Fazer login
    @PostMapping("/api/cliente/login")
    public Cliente fazerLogin(@RequestBody Cliente obj){
        Optional<Cliente> retorno = bd.fazerLogin(obj.getEmail(), obj.getSenha()) ;
        if(retorno.isPresent()) {
            return retorno.get();
        }  else {
            return new Cliente();
        }
    }
}
