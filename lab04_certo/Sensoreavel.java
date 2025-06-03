import java.util.ArrayList;

public interface Sensoreavel {
    void acionarSensores(Ambiente ambiente) throws RoboDesligadoException;
    ArrayList<Sensor> getSensores();
}
