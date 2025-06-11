package model;

import java.util.ArrayList;
import java.util.List;

public class BancoUsuarios {
    private static List<Usuario> usuarios = new ArrayList<>();

    static {
        // Usuários padrão
        usuarios.add(new Usuario("admin", "admin123", true));
        usuarios.add(new Usuario("user", "user123", false));
    }

    public static void cadastrar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static Usuario autenticar(String nome, String senha) {
        return usuarios.stream()
                .filter(u -> u.getNome().equals(nome) && u.getSenha().equals(senha))
                .findFirst().orElse(null);
    }

    public static boolean existe(String nome) {
        return usuarios.stream().anyMatch(u -> u.getNome().equals(nome));
    }
}
