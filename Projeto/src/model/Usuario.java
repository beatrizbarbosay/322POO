package model;

public class Usuario {
    private String nome;
    private String senha;
    private boolean admin;

    public Usuario(String nome, String senha, boolean admin) {
        this.nome = nome;
        this.senha = senha;
        this.admin = admin;
    }

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
