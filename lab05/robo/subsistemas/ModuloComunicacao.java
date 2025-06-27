package robo.subsistemas;

import comunicacao.CentralComunicacao;
import comunicacao.Comunicavel;
import comunicacao.ErroComunicacaoException;
import robo.Robo;
import robo.RoboAereoFantasma;
import robo.RoboDesligadoException;

/**
 * Subsistema responsável por gerenciar a comunicação de um robô.
 */
public class ModuloComunicacao {

    private Robo robo;

    public ModuloComunicacao(Robo robo) {
        this.robo = robo;
    }

    public void enviarMensagem(Comunicavel destinatario, String mensagem, CentralComunicacao central) 
            throws RoboDesligadoException, ErroComunicacaoException {
        
        if (!robo.estaLigado()) {
            throw new RoboDesligadoException("Robô " + robo.retornarNome() + " está desligado e não pode enviar mensagens.");
        }
        if (destinatario == null || !(destinatario instanceof Robo)) {
            throw new ErroComunicacaoException("Destinatário inválido.");
        }

        String idRemetente = robo.retornarNome();
        String idDestinatario = ((Robo) destinatario).retornarNome();
        String mensagemFormatada = mensagem;
        
        if (this.robo instanceof RoboAereoFantasma) {
            System.out.println("ModuloComunicacao: " + idRemetente + " (Fantasma) sussurrou para " + idDestinatario + ": " + mensagem);
            mensagemFormatada = "[Sussurro Fantasma] " + mensagem;
        } else {
            System.out.println("ModuloComunicacao: " + idRemetente + " enviou para " + idDestinatario + ": " + mensagem);
        }

        central.registrarMensagem(idRemetente, mensagemFormatada);
        destinatario.receberMensagem(mensagemFormatada, idRemetente);
    }

    public void receberMensagem(String mensagem, String idRemetente) throws RoboDesligadoException {
        if (!robo.estaLigado()) {
            throw new RoboDesligadoException("Robô " + robo.retornarNome() + " está desligado e não pode receber mensagens.");
        }
        System.out.println("ModuloComunicacao: " + robo.retornarNome() + " recebeu de " + idRemetente + ": " + mensagem);
    }
}