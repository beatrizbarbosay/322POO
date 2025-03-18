public class Robo {
    private String nome;
    private int posicaoX;
    private int posicaoY;

    // Construtor
    public Robo(String n, int x, int y) {
        nome = n;
        posicaoX = x;
        posicaoY = y;
    }

    // Método para mover o robô
    public void mover(int deltaX, int deltaY) {
        posicaoX += deltaX;
        posicaoY += deltaY;
    }
    public int retornarX() {
        return posicaoX;
    }
    public int retornarY() {
        return posicaoY;
    }

    // Método para exibir a posição do robô
    public void exibirPosicao() {
        System.out.println("Robo " + nome + " está na posição (" + posicaoX + ", " + posicaoY + ")");
    }
}
