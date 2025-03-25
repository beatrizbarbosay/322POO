public class RoboAereoFantasma extends RoboAereo { //uma subclasse de robo aereo que possui invisibilidade e nao é detectado por outros robos
    public RoboAereoFantasma(String n, int x, int y, String d, int a, int am) {
        super(n, x, y, d, a, am); //herda de RoboAereo
    }

    @Override
    public void identificarObstaculo() { //ele "ultrapassa" obstaculos
        System.out.println("Robo " + retornarNome() + "é um robô aéreo fantasma e não identifica obstáculos");
    }    
}
