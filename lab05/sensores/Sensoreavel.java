package sensores;
import java.util.ArrayList;

import ambiente.Ambiente;
import robo.RoboDesligadoException;

public interface Sensoreavel {
    void acionarSensores(Ambiente ambiente) throws RoboDesligadoException;
    ArrayList<Sensor> getSensores();
}
