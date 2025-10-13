import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-contato',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './contato.component.html',
  styleUrl: './contato.component.css'
})
export class ContatoComponent {
    nome:string="";
    email:string="";
    dataPedido:string="";
    numeroPedido:number=0;
    assunto:string="";
    mensagem:string="";
    telefone:string="";
    recebeCopia:number=0;
    autoriza:number=0;
    msg:string="";
    enviar(){
      if(this.autoriza){
        let corpoEmail = "nome="+ this.nome + "<br> "+
        "email="+    this.email + "<br> "+
        "data pedido="+    this.dataPedido+ "<br> "+
        "numero pedido="+    this.numeroPedido+ "<br> "+
        "assunto="+    this.assunto+ "<br> "+
        "mensagem="+    this.mensagem+ "<br> "+
        "telefone="+    this.telefone+ "<br> "+
        "recebe copia="+    this.recebeCopia+ "<br> "+
        "autoriza="+    this.autoriza;
        console.log(corpoEmail);
        localStorage.setItem("faleConosco", corpoEmail);
        this.msg = "sua mensagem foi enviada com sucesso, em breve entraremos em contato!";
      } else {
        this.msg = "você precisa autorizar o uso dos dados";
      }
    }
}
