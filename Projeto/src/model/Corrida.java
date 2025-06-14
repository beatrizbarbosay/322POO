package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Representa uma corrida com participantes (pilotos + carros).
public class Corrida implements Serializable {
    private String nome;  // Nome da corrida.
    private String local;  // Local da corrida.
    private double distancia;  // Distância em km.
    private List<ParticipanteCorrida> participantes;  // Pilotos e carros inscritos.

    public Corrida(String nome, String local, double distancia) {
        this.nome = nome;
        this.local = local;
        this.distancia = distancia;
        this.participantes = new ArrayList<>();
    }

    // Métodos de acesso.
    public String getNome() {
        return nome;
    }
    public String getLocal() {
        return local;
    }
    public double getDistancia() {
        return distancia;
    }
    public List<ParticipanteCorrida> getParticipantes() {
        return participantes;
    }

    // Adiciona um novo participante (piloto + carro).
    public void adicionarParticipante(Piloto piloto, Carro carro) {
        participantes.add(new ParticipanteCorrida(piloto, carro));
    }

    @Override
    // Formato: "Nome - Local (X km)".
    public String toString() {
        return nome + " - " + local + " (" + distancia + " km)";
    }
}