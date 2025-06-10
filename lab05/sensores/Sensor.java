package sensores;
import ambiente.Ambiente;
import robo.Robo;

public abstract class Sensor { 
    protected double raio;

    public String getTipoSensor() {
        return "Sensor genérico";
    }

    public Sensor(double raio) { 
        this.raio = raio;
    }

    public double getRaio() {
        return raio;
    }

    public abstract void monitorar(Robo robo, Ambiente ambiente); //para acionar os sensores
}