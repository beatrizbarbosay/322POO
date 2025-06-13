package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Corrida implements Serializable {
    private String nome;
    private String local;
    private double distancia;
    private List<ParticipanteCorrida> participantes;

    public Corrida(String nome, String local, double distancia) {
        this.nome = nome;
        this.local = local;
        this.distancia = distancia;
        this.participantes = new ArrayList<>();
    }

    // Métodos getters
    public String getNome() { return nome; }
    public String getLocal() { return local; }
    public double getDistancia() { return distancia; }
    public List<ParticipanteCorrida> getParticipantes() { return participantes; }

    // Método para adicionar participante
    public void adicionarParticipante(Piloto piloto, Carro carro) {
        participantes.add(new ParticipanteCorrida(piloto, carro));
    }

    @Override
    public String toString() {
        return nome + " - " + local + " (" + distancia + " km)";
    }
}