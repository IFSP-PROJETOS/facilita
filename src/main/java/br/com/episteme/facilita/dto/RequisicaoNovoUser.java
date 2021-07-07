package br.com.episteme.facilita.dto;

import br.com.episteme.facilita.models.User;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


// É uma classe DTO (Data Transfer Object)
public class RequisicaoNovoUser {
    @NotBlank
    @NotNull
    private String nome; // em caso de erro, NotBlank.requisicaoNovoProfessor.nome
    @NotNull
    private String email;
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public User toUser(){
        User u = new User();
        u.setNome(this.nome);
        u.setEmail(this.email);
        u.setSenha(this.senha);
        return u;
    }
    @Override
    public String toString() {
        return "RequisicaoNovoUser{" +
                "nome='" + nome + '\'' +
                ", email=" + email +
                '}';
    }
}