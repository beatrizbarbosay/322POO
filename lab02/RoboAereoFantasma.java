// Classe que herda de RoboAereo e possui invisibilidade (não é detectado por outros robôs)
public class RoboAereoFantasma extends RoboAereo {

    // Construtor que recebe o nome, posição, direção, altitude
    public RoboAereoFantasma(String n, int x, int y, String d, int a, int am) {
        super(n, x, y, d, a, am); // Chama o construtor da classe RoboTerrestre
    }

    @Override
    public void identificarObstaculo(Robo outroRobo ) { //ele "ultrapassa" obstaculos
        System.out.println("Robô " + retornarNome() + " é um robô aéreo fantasma e não identifica obstáculos");
    }    
}