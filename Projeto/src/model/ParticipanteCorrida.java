package model;

import java.io.Serializable;

// Representa a combinação piloto + carro em uma corrida.
public class ParticipanteCorrida implements Serializable {
    private Piloto piloto;
    private Carro carro;

    public ParticipanteCorrida(Piloto piloto, Carro carro) {
        this.piloto = piloto;
        this.carro = carro;
    }

    // Métodos de acesso.
    public Piloto getPiloto() {
        return piloto;
    }
    public Carro getCarro() {
        return carro;
    }

    @Override
    // Formato: "NomePiloto - ModeloCarro".
    public String toString() {
        return piloto.getNome() + " - " + carro.getModelo();
    }

}