package robo.subsistemas;

import ambiente.Ambiente;
import robo.Robo;
import robo.RoboTerrestre; // Precisamos para checar a velocidade
import exceptions.AcaoNaoPermitidaException;
import robo.RoboDesligadoException;

public class ControleMovimento {
    private Robo robo;
    public ControleMovimento(Robo robo) {
        this.robo = robo;
    }

    public void moverPara(int novoX, int novoY, int novoZ, Ambiente ambiente) throws AcaoNaoPermitidaException, RoboDesligadoException {
        if (!robo.estaLigado()) {
            throw new RoboDesligadoException("Robô " + robo.retornarNome() + " está desligado.");
        }

        if (robo instanceof RoboTerrestre) {
            RoboTerrestre roboTerrestre = (RoboTerrestre) robo;
            if (novoZ != robo.getZ()) {
                 throw new AcaoNaoPermitidaException("Robô terrestre não pode mudar de altitude.");
            }
            
            int deltaX = novoX - robo.getX();
            int deltaY = novoY - robo.getY();

            if (Math.abs(deltaX) > roboTerrestre.retornarVelocidadeMaxima() || Math.abs(deltaY) > roboTerrestre.retornarVelocidadeMaxima()) {
                 throw new AcaoNaoPermitidaException("Movimento excede a velocidade máxima.");
            }
        }
        
        System.out.println("ControleMovimento: Movimento para ("+novoX+","+novoY+","+novoZ+") validado.");
    }
}