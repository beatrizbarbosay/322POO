// Classe que herda de RoboAereo e possui invisibilidade (não é detectado por outros robôs)
public class RoboAereoFantasma extends RoboAereo implements Comunicavel{

    // Construtor que recebe o nome, posição, direção, altitude
    public RoboAereoFantasma(String n, int x, int y, String d, int a, int am) {
        super(n, x, y, d, a, am); // Chama o construtor da classe RoboTerrestre
    }

    @Override
    public void identificarObstaculo(Robo outroRobo ) { //ele "ultrapassa" obstaculos
        System.out.println("Robô " + retornarNome() + " é um robô aéreo fantasma e não identifica obstáculos");
    }
    
    @Override
    public void enviarMensagem(String mensagem, Robo destinatario) {
        System.out.println("Robo Fantasma " + retornarNome() + " enviou a mensagem: " + mensagem + " para " + destinatario.retornarNome());
    }

    @Override
    public void receberMensagem(String mensagem, Robo remetente) {
        System.out.println("Robo Fantasma " + retornarNome() + " recebeu a mensagem: " + mensagem + " de " + remetente.retornarNome());
    }
}