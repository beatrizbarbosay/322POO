package comunicacao;
import robo.RoboDesligadoException;

public interface Comunicavel {
    void enviarMensagem(Comunicavel destinatario, String mensagem, CentralComunicacao central)
            throws RoboDesligadoException, ErroComunicacaoException;
    void receberMensagem(String mensagem, String idRemetente) 
            throws RoboDesligadoException;
}

