package com.fatec.loja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class LojaService {
    @Autowired
    private JavaMailSender conta;

    // enviar email do produto
    public String enviarEmail(String to, String assunto, String corpo){
        try{
            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setFrom("loja2025ads@gmail.com");
            mensagem.setTo(to);
            mensagem.setSubject(assunto);
            mensagem.setText(corpo);
            conta.send(mensagem);
            return "";
        }
        catch(Exception err){
            return "Ocorreu um erro durante o envio do email !" + err.getMessage();
        }

    }

    // confirmar de cadastro
    public String enviarEmailConfirmacao(String email, String nome) {
        String assunto = "🎉 Confirmação de Cadastro!";
        String corpo = gerarMensagemConfirmacao(nome);
        return enviarEmail(email, assunto, corpo);
    }

    private String gerarMensagemConfirmacao(String nome) {
        return "Olá " + nome + "!\n\n" +
               "Seu cadastro foi realizado com sucesso em nossa loja!\n\n" +
               "Agradecemos por escolher nossa loja!\n\n" +
               "Atenciosamente,\nEquipe Sua Loja";
    }

}
