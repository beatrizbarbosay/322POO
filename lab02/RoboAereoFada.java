// Classe que herda de RoboAereo e representa um robô fada que muda de cor
public class RoboAereoFada extends RoboAereo {
    private String corBrilho; //Cor atual
    
    // Construtor que recebe o nome, posição, direção, altitude e a cor
    public RoboAereoFada(String n, int x, int y, String d, int a, int am, String c) {        
        super(n, x, y, d, a, am); // Chama o construtor da classe RoboTerrestre
        corBrilho = c; // Define a cor do brilho da fada
    }

    // Método para mudar a cor do brilho
    public void mudarCor(String novaCor) {
        corBrilho = novaCor;
        System.out.println("Robo Fada " + retornarNome() + " mudou a cor do brilho para " + corBrilho);
    }

    // Método que mostra a cor que a fada está brilhando
    public void brilhar() {
        System.out.println("Robo Fada " + retornarNome() + " está brilhando na cor " + corBrilho);
    }

    // Exibe a cor que a fada está brilhando e a sua altitude
    @Override
    public void exibirPosicao() {
        super.exibirPosicao();
        brilhar();
    }

    
}