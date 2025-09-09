package com.fatec.loja;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    LojaService lojaService;

    // gravar cliente
    @PostMapping("/api/cliente")
    public ResponseEntity<Map<String, String>> gravar(@RequestBody Cliente obj){
    Map<String, String> response = new HashMap<>();
    
    try {
        bd.save(obj);
        response.put("status", "success");
        response.put("message", "Cliente cadastrado com sucesso");
        response.put("clienteId", String.valueOf(obj.getId()));
        
        // Envia email em segundo plano (não bloqueia a resposta)
        new Thread(() -> {
            String resultadoEmail = lojaService.enviarEmailConfirmacao(
                obj.getEmail(), 
                obj.getNome()
            );
            System.out.println("Status do email: " + resultadoEmail);
        }).start();
        
        response.put("emailStatus", "enviando_em_segundo_plano");
        return ResponseEntity.ok(response);
        
    } catch (Exception error) {
        response.put("status", "error");
        response.put("message", "Erro ao cadastrar cliente: " + error.getMessage());
        return ResponseEntity.status(500).body(response);
    }
}

    // carregar cliente
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
