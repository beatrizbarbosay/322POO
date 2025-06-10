package robo;
import java.util.ArrayList;

import ambiente.Ambiente;
import comunicacao.CentralComunicacao;
import comunicacao.Comunicavel;
import comunicacao.ErroComunicacaoException;
import exceptions.AcaoNaoPermitidaException; 
public class RoboAereoFantasma extends RoboAereo implements Comunicavel { // Adicionado Comunicavel

    public RoboAereoFantasma(String n, int x, int y, String d, int a, int am) {
        super(n, x, y, d, a, am);
    }

    public void identificarObstaculo(Robo outroRobo ) {
        System.out.println("Robô " + retornarNome() + " é um robô aéreo fantasma e não se importa com obstáculos ou outros robôs.");
    }
    
    @Override
    public void executarTarefa(Ambiente ambiente) throws RoboDesligadoException, AcaoNaoPermitidaException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " desligado.");
        }
        System.out.println("Robô Aéreo Fantasma " + retornarNome() + " está flutuando... Buuu!");
        // Tentar se comunicar com alguém
        ArrayList<Robo> robos = ambiente.getRobos();
        if(robos.size() > 1){
            for(Robo r : robos){
                if(r != this && r instanceof Comunicavel){
                    try {
                        enviarMensagem((Comunicavel)r, "Eu vejo vocêuuu...", new CentralComunicacao()); 
                    } catch (ErroComunicacaoException ece) {
                    }
                    break;
                }
            }
        }
    }

    // Implementação de Comunicavel
    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem, CentralComunicacao central)
            throws RoboDesligadoException, ErroComunicacaoException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " (Fantasma) está desligado.");
        }
        if (destinatario == null) {
            throw new ErroComunicacaoException("Destinatário da mensagem é nulo.");
        }
        if (!(destinatario instanceof Robo)) {
             throw new ErroComunicacaoException("Destinatário não é um Robô ou não pode ser identificado.");
        }
        String idDestinatario = ((Robo) destinatario).retornarNome();

        System.out.println("Robô Fantasma " + retornarNome() + " sussurrou para " + idDestinatario + ": " + mensagem);
        central.registrarMensagem(this.retornarNome(), "[Sussurro Fantasma] " + mensagem); // Adiciona à central
        destinatario.receberMensagem("[Sussurro Fantasma] " + mensagem, this.retornarNome());
    }

    @Override
    public void receberMensagem(String mensagem, String idRemetente) throws RoboDesligadoException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " (Fantasma) está desligado.");
        }
        System.out.println("Robô Fantasma " + retornarNome() + " sentiu uma presença comunicando (de " + idRemetente + "): " + mensagem);
    }
    
}