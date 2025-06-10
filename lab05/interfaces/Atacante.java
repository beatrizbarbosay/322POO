package interfaces;
import ambiente.Ambiente;
import exceptions.SobreCargaExplosivaException;
import robo.RoboDesligadoException;

public interface Atacante {
    void atacar(int x, int y, Ambiente ambiente) throws SobreCargaExplosivaException, RoboDesligadoException;
}

