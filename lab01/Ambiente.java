public class Ambiente {
    private int largura;
    private int altura;

    // Construtor
    public Ambiente(int x, int y) {
        largura = x;
        altura = y;
    }

    // Método para verificar se uma posição está dentro dos limites do ambiente
    public boolean dentroDosLimites(int x, int y) {
        return x <= largura && y <= altura;
    }
}

