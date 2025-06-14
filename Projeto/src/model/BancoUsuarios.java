package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Gerencia usuários do sistema (em memória, sem persistência).
public class BancoUsuarios {
    private static final String ARQUIVO = "usuarios.dat";
    private static List<Usuario> usuarios = carregarDados();

    // Carrega usuários do arquivo ou retorna lista vazia
    private static List<Usuario> carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (List<Usuario>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Primeira execução: cria usuários padrão
            List<Usuario> padrao = new ArrayList<>();
            padrao.add(new Usuario("admin", "admin123", true));
            padrao.add(new Usuario("user", "user123", false));
            return padrao;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Salva usuários no arquivo
    private static void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    // Cadastra um novo usuário.
    public static void cadastrar(Usuario usuario) {
        usuarios.add(usuario);
        salvarDados(); 
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