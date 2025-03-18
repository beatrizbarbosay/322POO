class Robo {
    //define a classe
    private String nome;
    private int posicaoX;
    private int posicaoY;
//funcao de atribuicao
    public Robo(String nomedorobo, int x, int y){
        nome= nomedorobo;
        posicaoX= x;
        posicaoY = y;
    }
//metodos
    public void mover (int deltaX, int deltaY){
        posicaoX+=deltaX;
        posicaoY+=deltaY;
    }
    public void exibirPosicao(){
        System.out.println(nome + "está na posição("+ posicaoX +", "+ posicaoY +")");
    }

}
class Ambiente{

    private int largura;
    private int altura;

    public Ambiente(int x, int y){
        largura = x;
        altura = y;
    }

    public boolean dentroDosLimites(int x, int y){
        return x <= largura && y <= altura;
    }

}

public class Main {
    public static void main(String[] args) {
        // Criando o ambiente de tamanho 10x10
        Ambiente ambiente = new Ambiente(10, 10);

        // Criando um robô na posição (5,5)
        Robo robo1 = new Robo("Alpha", 5, 5);

        // Exibindo a posição inicial
        robo1.exibirPosicao();

        // Tentando mover o robô
        int novoX = 5 + 3;  // Tentativa de mover para a direita (x+3)
        int novoY = 5 + 4;  // Tentativa de mover para cima (y+4)

        // Verificando se o novo movimento está dentro dos limites
        if (ambiente.dentroDosLimites(novoX, novoY)) {
            robo1.mover(3, 4);
            System.out.println("Movimento permitido!");
        } else {
            System.out.println("Movimento inválido! O robô sairia dos limites.");
        }

        // Exibindo a nova posição do robô
        robo1.exibirPosicao();

        // Testando um movimento que deve falhar
        int novoX2 = 8 + 5;  // Tentativa de mover além do limite (x+5)
        int novoY2 = 9 + 3;  // Tentativa de mover além do limite (y+3)

        if (ambiente.dentroDosLimites(novoX2, novoY2)) {
            robo1.mover(5, 3);
            System.out.println("Movimento permitido!");
        } else {
            System.out.println("Movimento inválido! O robô sairia dos limites.");
        }

        // Exibindo a posição final do robô
        robo1.exibirPosicao();
    }
}
