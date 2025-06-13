package model;

import java.io.Serializable;

public class ParticipanteCorrida implements Serializable {
    private Piloto piloto;
    private Carro carro;

    public ParticipanteCorrida(Piloto piloto, Carro carro) {
        this.piloto = piloto;
        this.carro = carro;
    }

    // Métodos getters
    public Piloto getPiloto() { return piloto; }
    public Carro getCarro() { return carro; }

    @Override
    public String toString() {
        return piloto.getNome() + " - " + carro.getModelo();
    }
}