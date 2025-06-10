package robo.subsistemas;

import java.util.ArrayList;
import ambiente.Ambiente;
import robo.Robo;
import robo.RoboDesligadoException;
import sensores.Sensor;

/**
 * Subsistema responsável por gerenciar a coleção de sensores de um robô
 * e por acioná-los.
 */
public class GerenciadorSensores {

    private Robo robo;
    private ArrayList<Sensor> sensores;

    public GerenciadorSensores(Robo robo) {
        this.robo = robo;
        this.sensores = new ArrayList<>();
    }

    public void adicionarSensor(Sensor sensor) {
        this.sensores.add(sensor);
    }

    public ArrayList<Sensor> getSensores() {
        return this.sensores;
    }

    public void acionarSensores(Ambiente ambiente) throws RoboDesligadoException {
        if (!robo.estaLigado()) {
            throw new RoboDesligadoException("Robô " + robo.retornarNome() + " está desligado e não pode acionar sensores.");
        }
        
        System.out.println("--- Sensores de " + robo.retornarNome() + " ---");
        if (sensores.isEmpty()) {
            System.out.println("Nenhum sensor para acionar.");
        } else {
            for (Sensor sensor : sensores) {
                sensor.monitorar(this.robo, ambiente);
            }
        }
        System.out.println("---------------------------------");
    }
}
