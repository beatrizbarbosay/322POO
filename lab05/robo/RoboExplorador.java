package robo;

import ambiente.Ambiente;
import ambiente.ColisaoException;
import ambiente.ForaDosLimitesException;
import exceptions.AcaoNaoPermitidaException;
import exceptions.EnergiaCuraException;
import robo.RoboDesligadoException;
import exceptions.SobreCargaExplosivaException;
import missao.Missao;


// Um RoboExplorador é um AgenteInteligente terrestre.
public class RoboExplorador extends AgenteInteligente {

    private int velocidadeMaxima;

    public RoboExplorador(String n, int x, int y, String d, int v) {
        super(n, x, y, 0, d); // Construtor do AgenteInteligente
        this.velocidadeMaxima = v;
    }

    // Implementação do método abstrato de AgenteInteligente
    @Override
    public void executarMissao(Ambiente a) {
        if (temMissao()) {
            System.out.println("RoboExplorador " + this.retornarNome() + " iniciando sua missão...");
            missao.executar(this, a);
        } else {
            System.out.println("RoboExplorador " + this.retornarNome() + " não tem nenhuma missão definida.");
        }
    }

    @Override
    public void moverPara(int novoX, int novoY, int novoZ, Ambiente ambiente) 
            throws RoboDesligadoException, AcaoNaoPermitidaException, ForaDosLimitesException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " está desligado.");
        }
        if (novoZ != this.posicaoZ) { 
             throw new AcaoNaoPermitidaException("Robô explorador (terrestre) não pode mudar de altitude.");
        }
        
        ambiente.dentroDosLimites(novoX, novoY, novoZ);

        int deltaX = novoX - this.posicaoX;
        int deltaY = novoY - this.posicaoY;

        if (Math.abs(deltaX) > velocidadeMaxima || Math.abs(deltaY) > velocidadeMaxima) {
             throw new AcaoNaoPermitidaException("Movimento excede a velocidade máxima de " + velocidadeMaxima);
        }

        this.posicaoX = novoX;
        this.posicaoY = novoY;
    }

    @Override
    public void executarTarefa(Ambiente ambiente) throws RoboDesligadoException, AcaoNaoPermitidaException, EnergiaCuraException, SobreCargaExplosivaException {
        executarMissao(ambiente);
    }
}