import { Component } from '@angular/core';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent {
  // clientes armazenados no localStorage
  clientes: any[] = [];

  constructor() {
    const dados = localStorage.getItem('clientes');
    if (dados) {
      this.clientes = JSON.parse(dados);
    }
  }

  cadastrar(nomeInput: HTMLInputElement, emailInput: HTMLInputElement, telefoneInput: HTMLInputElement) {
    const nome = nomeInput.value.trim();
    const email = emailInput.value.trim();
    const telefone = telefoneInput.value.trim();

    if (!nome || !email) {
      alert('Nome e Email são obrigatórios!');
      return;
    }

    const novoCliente = { nome, email, telefone };
    this.clientes.push(novoCliente);

    // salva no localStorage
    localStorage.setItem('clientes', JSON.stringify(this.clientes));

    // adiciona direto no HTML
    const lista = document.getElementById('lista-clientes')!;
    const li = document.createElement('li');
    li.textContent = `${nome} - ${email} - ${telefone}`;
    lista.appendChild(li);

    // limpa campos
    nomeInput.value = '';
    emailInput.value = '';
    telefoneInput.value = '';
  }
}
