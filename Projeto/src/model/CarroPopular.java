//Representa um carro polular
package model;
public class CarroPopular extends Carro {
    public CarroPopular(String modelo, double velocidadeMax) {
        super(modelo, velocidadeMax);
    }

    @Override
    public String getTipo() {
        return "Carro Popular";
    }
}