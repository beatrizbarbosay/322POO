//Representa um carro esportivo
public class CarroEsportivo extends Carro {
    public CarroEsportivo(String modelo, double velocidadeMax) {
        super(modelo, velocidadeMax);
    }

    @Override
    public String getTipo() {
        return "Carro Esportivo";
    }
}