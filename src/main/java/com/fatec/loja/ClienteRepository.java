package com.fatec.loja;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ClienteRepository extends 
JpaRepository<Cliente, Integer>{
    @Query(value = "select * from where email=1 and senha=2",
    nativeQuery = true);
    public Optional<Cliente> fazerLogin(String email, String senha);

    @Query(value="select * from cliente where ativo=0", nativeQuery = true)
    public List<Cliente> listarInvativos();

    @GetMapping("/api/cliente/inativos")
    public List<Cliente> carregarInativo(){
        return bd.listarInvativos();
    }
    @PostMapping("/api/cliente/login")
    public Cliente fazerLogin(@RequestBody Cliente db){
        Optional<Cliente> retorno = bd.fazerLogin(obj.getEmail(), obj.getSenha());
        if(retorno.isPresent()) 
            return retorno.get();
        else 
            return new Cliente();
    }
}
    