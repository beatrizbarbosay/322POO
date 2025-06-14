package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Gerencia a persistência de corridas em arquivo binário.
public class BancoCorridas {
    private static final String ARQUIVO = "corridas.dat";
    private static List<Corrida> corridas = carregarDados();

    // Adiciona uma nova corrida e salva automaticamente.
    public static void adicionarCorrida(Corrida corrida) {
        corridas.add(corrida);
        salvarDados();
    }

    // Retorna cópia da lista de corridas.
    public static List<Corrida> getTodasCorridas() {
        return new ArrayList<>(corridas);
    }

    // Carrega corridas do arquivo ou retorna lista vazia.
    private static List<Corrida> carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (List<Corrida>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar corridas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Persiste a lista de corridas no arquivo.
    private static void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(corridas);
        } catch (IOException e) {
            System.err.println("Erro ao salvar corridas: " + e.getMessage());
        }
    }
}