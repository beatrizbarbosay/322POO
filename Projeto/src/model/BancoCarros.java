package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Gerencia a persistência de objetos Carro em arquivo binário.
public class BancoCarros {
    private static final String ARQUIVO = "carros.dat";
    private static List<Carro> carros = carregarDados();

    // Adiciona um novo carro e salva automaticamente no arquivo.
    public static void adicionarCarro(Carro carro) {
        carros.add(carro);
        salvarDados();
    }

    // Retorna uma cópia da lista de carros (evita modificações externas).
    public static List<Carro> getTodosCarros() {
        return new ArrayList<>(carros);
    }

    // Carrega os dados do arquivo ou retorna lista vazia se não existir.
    private static List<Carro> carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (List<Carro>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar carros: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Salva a lista atual de carros no arquivo.
    private static void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(carros);
        } catch (IOException e) {
            System.err.println("Erro ao salvar carros: " + e.getMessage());
        }
    }
}