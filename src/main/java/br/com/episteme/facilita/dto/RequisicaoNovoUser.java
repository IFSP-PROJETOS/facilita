package br.com.episteme.facilita.dto;

import br.com.episteme.facilita.models.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


// É uma classe DTO (Data Transfer Object)
public class RequisicaoNovoUser {
    @NotBlank
    private String nome; // em caso de erro, NotBlank.requisicaoNovoProfessor.nome
    @NotBlank @Email
    private String email;
    @NotBlank @Size(min=6,max=12)
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
        User user = new User();
        user.setNome(this.nome);
        user.setEmail(this.email);
        user.setSenha(this.senha);
        return user;
    }

    public User toUser(User user){
        user.setNome(this.nome);
        user.setEmail(this.email);
        user.setSenha(this.senha);
        return user;
    }

    public void formUser(User user) {
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.senha = user.getSenha();
    }

    @Override
    public String toString() {
        return "RequisicaoNovoUser{" +
                "nome='" + nome + '\'' +
                ", email=" + email +
                '}';
    }
}
