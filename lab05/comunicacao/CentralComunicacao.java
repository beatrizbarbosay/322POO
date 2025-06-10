package comunicacao;
import java.util.ArrayList;

public class CentralComunicacao {
    private ArrayList<String> mensagens; // Lista para armazenar mensagens enviadas
    public CentralComunicacao() {
        mensagens = new ArrayList<>(); // Inicializa a lista de mensagens
    }

    // Método para guardar as mensagens
    public void registrarMensagem(String remetente, String msg){
        String mensagem = "De: " + remetente + ": " + msg; // Formata a mensagem
        mensagens.add(mensagem); // Adiciona a mensagem à lista
    }
    // Método para exibir todas as mensagens registradas
    public void exibirMensagens() {
        System.out.println("Mensagens registradas:");
        if (mensagens.isEmpty()) {
            System.out.println("Nenhuma mensagem registrada."); // Exibe mensagem se não houver mensagens
        }else{
            for (String mensagem : mensagens) {
                System.out.println(mensagem); // Exibe cada mensagem
            }
        }
    }    
}
