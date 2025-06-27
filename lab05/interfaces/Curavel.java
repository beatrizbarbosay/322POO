package interfaces;
import exceptions.EnergiaCuraException;
import robo.Robo;
import robo.RoboDesligadoException;

public interface Curavel {
    void curar(Robo alvo) throws EnergiaCuraException, RoboDesligadoException;
}
