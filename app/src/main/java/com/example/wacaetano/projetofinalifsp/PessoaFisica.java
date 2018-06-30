//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Wilian Aparecido Caetano
//******************************************************

package com.example.wacaetano.projetofinalifsp;

public class PessoaFisica {

    private String nome;
    private String cpf;
    private String idade;
    private String telefone;
    private String email;

    //Construtor definido para popular atributos do objeto
    public PessoaFisica(String nome, String cpf, String idade, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
        this.email = email;
    }

    //Método foi subscrito para facilitar exibição de item da listagem obtida do banco de dados
    @Override
    public String toString() {
        return "Nome: " + getNome() + "\nCPF: " + getCpf() + "\nIdade: " + getIdade() + "\nTelefone: " + getTelefone() + "\ne-mail: " + getEmail();
    }

    //GETTERS E SETS

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
