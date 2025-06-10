package missao;

import ambiente.Ambiente;
import robo.Robo;
import sensores.Sensoreavel; 
import arquivos.Logger; // Importa nova classe Logger
import java.util.Random;

public class MissaoExplorar implements Missao {

    private Random random = new Random();

    @Override
    public void executar(Robo r, Ambiente a) {
        String nomeRobo = r.retornarNome();
        Logger.log("MissaoExplorar: Iniciada pelo robô " + nomeRobo + " na posição (" + r.getX() + "," + r.getY() + "," + r.getZ() + ")");
        if (r instanceof Sensoreavel) {
            try {
                Logger.log("MissaoExplorar: Ativando sensores de " + nomeRobo);
                ((Sensoreavel) r).acionarSensores(a);
            } catch (Exception e) {
                Logger.log("MissaoExplorar: Erro ao acionar sensores de " + nomeRobo + ": " + e.getMessage());
            }
        }

        // Mover aleatoriamente
        int novoX = r.getX();
        int novoY = r.getY();
        int direcao = random.nextInt(4);

        switch (direcao) {
            case 0: novoY++; break; // Norte
            case 1: novoX++; break; // Leste
            case 2: novoY--; break; // Sul
            case 3: novoX--; break; // Oeste
        }

        Logger.log("MissaoExplorar: " + nomeRobo + " tentando se mover para (" + novoX + ", " + novoY + ")");
        try {
            a.moverEntidade(r, novoX, novoY, r.getZ());
            Logger.log("MissaoExplorar: Movimento de " + nomeRobo + " bem-sucedido. Nova posição: (" + r.getX() + "," + r.getY() + ")");
        } catch (Exception e) {
            Logger.log("MissaoExplorar: Movimento de " + nomeRobo + " falhou. Motivo: " + e.getMessage());
        }
    }
}