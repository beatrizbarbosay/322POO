package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Representa uma corrida com participantes (pilotos + carros).
public class Corrida implements Serializable {
    private String nome;  // Nome da corrida
    private String local;  // Local
    private double distancia;  // Distância em km
    private List<ParticipanteCorrida> participantes;  // Pilotos e carros inscritos.
    private Map<Piloto, Double> temposEfetivos;
    private Map<Piloto, Double> temposIdeais;
    private boolean simulada;

    public Corrida(String nome, String local, double distancia) {
        this.nome = nome;
        this.local = local;
        this.distancia = distancia;
        this.participantes = new ArrayList<>();
        this.temposEfetivos = new HashMap<>();
        this.temposIdeais = new HashMap<>();
        this.simulada = false;
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

    public void simularCorrida() {
        temposEfetivos.clear();
        temposIdeais.clear();
        
        for (ParticipanteCorrida participante : participantes) {
            Piloto piloto = participante.getPiloto();
            Carro carro = participante.getCarro();
            
            double tempoIdeal = distancia / carro.getvelocidadeMax();
            temposIdeais.put(piloto, tempoIdeal);
            
            double fatorAleatorio = 0.8 + (Math.random() * 0.7);
            double tempoEfetivo = tempoIdeal * (1.5 - (piloto.getNivel() / 200)) * fatorAleatorio;
            temposEfetivos.put(piloto, tempoEfetivo);
            
            double desempenho = tempoIdeal / tempoEfetivo;
            desempenho = Math.min(desempenho, 1.0);
            piloto.atualizarNivel(desempenho);
            
            // Atualiza o piloto no banco de dados
            BancoPilotos.adicionarPiloto(piloto); // Isso sobrescreverá o piloto existente
        }
        
        temposEfetivos = temposEfetivos.entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(LinkedHashMap::new, 
                    (m, e) -> m.put(e.getKey(), e.getValue()), 
                    LinkedHashMap::putAll);
        
        this.simulada = true;
    
    }

    public boolean isSimulada() {
        return simulada;
    }

    public Map<Piloto, Double> getResultados() {
        return simulada ? new LinkedHashMap<>(temposEfetivos) : null;
    }

    public String getResultadosFormatados() {
        if (!simulada) return "Corrida não simulada ainda";
        
        StringBuilder sb = new StringBuilder();
        int posicao = 1;
        
        for (Map.Entry<Piloto, Double> entry : temposEfetivos.entrySet()) {
            Piloto piloto = entry.getKey();
            double tempoEfetivo = entry.getValue();
            double tempoIdeal = temposIdeais.get(piloto);
            double desempenho = tempoIdeal / tempoEfetivo;
            
            sb.append(String.format("%dº - %s | Tempo: %.2f segundos (Ideal: %.2f) | Desempenho: %.2f%%\n",
                    posicao++,
                    piloto.getNome(),
                    tempoEfetivo * 3600, // Convertendo horas para segundos
                    tempoIdeal * 3600,
                    desempenho * 100));
        }
        
        return sb.toString();
    }

    @Override
    // Formato: "Nome - Local (X km)".
    public String toString() {
        return nome + " - " + local + " (" + distancia + " km)";
    }
}