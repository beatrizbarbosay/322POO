// Classe que herda de RoboTerrestre e representa um robô minerador
import java.util.ArrayList;
class RoboMinerador extends RoboTerrestre implements Sensoreavel, Memorizavel, Comunicavel {
    private String tipoMineral; // Tipo de mineral que o robô minerador está buscando
    private Ambiente ambiente; // Ambiente onde o robô minerador está
    private ArrayList<String> memoria = new ArrayList<>(); // Memória do robô minerador

    // Construtor que recebe o nome, posição, direção, velocidade, tipo de mineral e o ambiente
    public RoboMinerador(String n, int x, int y, String d, int v, String m, Ambiente ambiente) {
        super(n, x, y, d, v); // Chama o construtor da classe RoboTerrestre
        tipoMineral = m; // Define o tipo de mineral que o robô minerador procura
        this.ambiente = ambiente; // Define o ambiente no qual o robô minerador opera
    }

    // Método para minerar o mineral no ambiente
    public void minerar() {
        // O robô só consegue minerar se o mineral que ele é capaz de minerar estiver em sua mesma coordenada
        if (ambiente.existeMineral(retornarX(), retornarY(), tipoMineral)) {
            System.out.println("Robo " + retornarNome() + " minerou " + tipoMineral + " na posição (" + retornarX() + ", " + retornarY() + ")");
            ambiente.removerMineral(retornarX(), retornarY()); // Remove o mineral do ambiente
        } else {
            System.out.println("Robo " + retornarNome() + " não encontrou " + tipoMineral + " para minerar na posição (" + retornarX() + ", " + retornarY() + ")");
        }
    }
    @Override
    public void memorizar(String evento, int x, int y) {
        String registro = "Evento: " + evento + " na posição (" + x + ", " + y + ")";
        memoria.add(registro);
        System.out.println("Robo " + this.retornarNome() + " memorizou: " + registro);
    }

    public void exibirMemoria() {
        System.out.println("Memória do robô " + this.retornarNome() + ":");
        for (String dado : memoria) {
            System.out.println(" - " + dado);
        }
    }
    @Override
    public void enviarMensagem(String mensagem, Robo destinatario) {
        System.out.println("Robo Fantasma " + retornarNome() + " enviou a mensagem: " + mensagem + " para " + destinatario.retornarNome());
    }

    @Override
    public void receberMensagem(String mensagem, Robo remetente) {
        System.out.println("Robo Fantasma " + retornarNome() + " recebeu a mensagem: " + mensagem + " de " + remetente.retornarNome());
    }

    @Override
    public void comunicar(String mensagem, CentralComunicacao central, Robo receptor) {
        if (!(receptor instanceof Comunicavel)) {
            throw new ErroComunicacaoException("Erro: o robô " + receptor.retornarNome() + " não é capaz de se comunicar!");
        }
    central.registrarMensagem(this.retornarNome(), mensagem);
    }
}
 