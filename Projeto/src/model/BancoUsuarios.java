package model;

import java.util.ArrayList;
import java.util.List;

// Gerencia usuários do sistema (em memória, sem persistência).
public class BancoUsuarios {
    private static List<Usuario> usuarios = new ArrayList<>();

    static {
        // Usuários padrão (admin e user comum).
        usuarios.add(new Usuario("admin", "admin123", true));
        usuarios.add(new Usuario("user", "user123", false));
    }

    // Cadastra um novo usuário.
    public static void cadastrar(Usuario usuario) {
        usuarios.add(usuario);
    }

    // Autentica usuário por nome e senha. Retorna null se falhar.
    public static Usuario autenticar(String nome, String senha) {
        return usuarios.stream()
                .filter(u -> u.getNome().equals(nome) && u.getSenha().equals(senha))
                .findFirst().orElse(null);
    }

    // Verifica se um nome de usuário já existe.
    public static boolean existe(String nome) {
        return usuarios.stream().anyMatch(u -> u.getNome().equals(nome));
    }
}