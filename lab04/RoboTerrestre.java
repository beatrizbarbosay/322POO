// Classe que herda de Robo e representa robôs terrestres
public class RoboTerrestre extends Robo {
    private int velocidadeMaxima; // Velocidade máxima do robô terrestre

    // Construtor que recebe o nome, posição, direção, velocidade máxima e inicializa a classe base Robo
    public RoboTerrestre(String n, int x, int y, String d, int v) {
        super(n, x, y, d); // Chama o construtor da classe Robo
        velocidadeMaxima = v;  // Define a velocidade máxima do robô terrestre
    }

    // Método para acessar a velocidade máxima do robô terrestre
    public int retornarVelocidadeMaxima() {
        return velocidadeMaxima;
    }

     // Método sobrescrito para mover o robô terrestre
    @Override
    public void mover(int deltaX, int deltaY) {
        if (Math.abs(deltaX) <= velocidadeMaxima && Math.abs(deltaY) <= velocidadeMaxima) {
            super.mover(deltaX, deltaY); // O robô só move se a distância X e Y a ser percorrida for menor do que a velocidade máxima
        } else {
            System.out.println("Velocidade máxima excedida");
        }
    }
}