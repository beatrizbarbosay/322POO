package model;

import java.io.Serializable;

// Representa um usuário do sistema (admin ou comum).
public class Usuario implements Serializable {
    private String nome;
    private String senha;
    private boolean admin;  // Define se o usuário é administrador

    public Usuario(String nome, String senha, boolean admin) {
        this.nome = nome;
        this.senha = senha;
        this.admin = admin;
    }

    // Métodos de acesso.
    public String getNome() {
        return nome;
    }
    public String getSenha() {
        return senha;
    }
    public boolean isAdmin() {
        return admin;
    }
}