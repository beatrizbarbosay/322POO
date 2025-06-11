package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BancoCarros {
    private static final String ARQUIVO = "carros.dat";
    private static List<Carro> carros = carregarDados();

    public static void adicionarCarro(Carro carro) {
        carros.add(carro);
        salvarDados();
    }

    public static List<Carro> getTodosCarros() {
        return new ArrayList<>(carros);
    }

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

    private static void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(carros);
        } catch (IOException e) {
            System.err.println("Erro ao salvar carros: " + e.getMessage());
        }
    }
}