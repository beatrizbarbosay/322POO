public class Robo {
    private String nome;
    private int posicaoX;
    private int posicaoY;
    private String direcao;

    // Construtor
    public Robo(String n, int x, int y, String d) {
        nome = n;
        posicaoX = x;
        posicaoY = y;
        direcao = d;
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
    public String retornarNome() {
        return nome;
    }
    
    
    // Método para exibir a posição do robô
    public void exibirPosicao() {
        System.out.println("Robo " + nome + " está na posição (" + posicaoX + ", " + posicaoY + ")");
    }

    public void identificarObstaculo(Robo outroRobo) { //identifica outros robos mas ignora se for um robo fantasma
        if (outroRobo instanceof RoboAereoFantasma) {
            System.out.println("Robo " + retornarNome() + " não detecta obstáculos fantasmas.");
        } else {
            System.out.println("Robo " + retornarNome() + " detectou um obstáculo na posição (" + outroRobo.retornarX() + ", " + outroRobo.retornarY() + ")");
        }
    }
    
}
