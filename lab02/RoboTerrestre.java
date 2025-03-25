public class RoboTerrestre extends Robo {
    private int velocidadeMaxima;

    public RoboTerrestre(String n, int x, int y, String d, int v) {
        super(n, x, y, d);
        velocidadeMaxima = v;
    }

    public int retornarVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    @Override
    public void mover(int deltaX, int deltaY) {
        if (Math.abs(deltaX) <= velocidadeMaxima && Math.abs(deltaY) <= velocidadeMaxima) {
            super.mover(deltaX, deltaY);
        } else {
            System.out.println("Velocidade máxima excedida");
        }
    }
}