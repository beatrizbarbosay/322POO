package robo;

import ambiente.Ambiente;
import exceptions.*;
public class RoboTerrestre extends Robo {
    private int velocidadeMaxima;

    public RoboTerrestre(String n, int x, int y, String d, int v) {
        super(n, x, y, 0, d);
        this.velocidadeMaxima = v;
    }
    
    @Override
    public void executarTarefa(Ambiente ambiente) throws RoboDesligadoException, AcaoNaoPermitidaException, EnergiaCuraException, SobreCargaExplosivaException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " está desligado.");
        }
        System.out.println("Robô Terrestre " + retornarNome() + " executando sua tarefa: Patrulhando perímetro.");
    }
    
    public int retornarVelocidadeMaxima() {
        return velocidadeMaxima;
    }
}