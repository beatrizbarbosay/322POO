//Representa um carro genérico (superclasse de carros populares e esportivos).
public abstract class Carro {
    //Atributos
    protected String modelo; //Modelo do Carro
    protected double velocidadeMax; //Valor para diferenciar performance dos carros

    //Método construtor
    public Carro(String modelo, double velocidadeMax) {
        this.modelo = modelo;
        this.velocidadeMax = velocidadeMax;
    }

    //Métodos getters
    public String getModelo() {
        return modelo;
    }
    public double getvelocidadeMax() {
        return velocidadeMax;
    }

    public abstract String getTipo(); //Seá implementado pelas subclasses.

    @Override
    //Exibe informações do carro
    public String toString() {
        return modelo + " [" + getTipo() + "]" + " - VelocidadeMáxima:" + velocidadeMax + "Km/h";
    }
}