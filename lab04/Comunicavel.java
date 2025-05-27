public interface Comunicavel {
    void enviarMensagem(String mensagem, Robo destinatario);
    void receberMensagem(String mensagem, Robo remetente);
}

