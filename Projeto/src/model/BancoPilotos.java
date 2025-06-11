package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BancoPilotos {
    private static final String ARQUIVO = "pilotos.dat";
    private static List<Piloto> pilotos = carregarDados();

    public static void adicionarPiloto(Piloto piloto) {
        pilotos.add(piloto);
        salvarDados();
    }

    public static List<Piloto> getTodosPilotos() {
        return new ArrayList<>(pilotos);
    }

    private static List<Piloto> carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (List<Piloto>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // Arquivo não existe ainda (primeira execução)
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar pilotos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(pilotos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar pilotos: " + e.getMessage());
        }
    }
}