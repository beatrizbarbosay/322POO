public interface Comunicavel {
    void enviarMensagem(Comunicavel destinatario, String mensagem, CentralComunicacao central)
            throws RoboDesligadoException, ErroComunicacaoException;
    void receberMensagem(String mensagem, String idRemetente) // Adicionado idRemetente para contexto
            throws RoboDesligadoException;
}

