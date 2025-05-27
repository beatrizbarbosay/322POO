public interface Comunicavel {
    void enviarMensagem(String mensagem, Robo destinatario);
    void receberMensagem(String mensagem, Robo remetente);
    void comunicar(String mensagem, CentralComunicacao central, Robo remetente) throws RoboDesligadoException, ColisaoException;
}

