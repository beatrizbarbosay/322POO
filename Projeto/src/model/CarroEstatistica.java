package model;

import javafx.beans.property.*;

// Classe para exibir dados de carros em tabelas JavaFX (com propriedades observáveis).
public class CarroEstatistica {
    private final StringProperty modelo;
    private final StringProperty tipo;
    private final DoubleProperty velocidadeMax;
    
    public CarroEstatistica(String modelo, String tipo, double velocidadeMax) {
        this.modelo = new SimpleStringProperty(modelo);
        this.tipo = new SimpleStringProperty(tipo);
        this.velocidadeMax = new SimpleDoubleProperty(velocidadeMax);
    }
    
    // Getters para propriedades (usados em bindings JavaFX).
    public StringProperty modeloProperty() {
        return modelo;
    }
    public StringProperty tipoProperty() {
        return tipo;
    }
    public DoubleProperty velocidadeMaxProperty() {
        return velocidadeMax;
    }
    
    // Getters convencionais.
    public String getModelo() {
        return modelo.get();
    }
    public String getTipo() {
        return tipo.get();
    }
    public double getVelocidadeMax() {
        return velocidadeMax.get();
    }
}