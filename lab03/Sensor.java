public abstract class Sensor {
    protected double raio;

    public Sensor(double raio) {
        this.raio = raio;
    }

    public double getRaio() {
        return raio;
    }

    public abstract void monitorar(Robo robo, Ambiente ambiente); // Método abstrato para monitorar obstáculos
       
}
