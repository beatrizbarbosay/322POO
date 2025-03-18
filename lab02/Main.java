public class Main {
    public static void main(String[] args) {
        // Criando o ambiente
        Ambiente ambiente = new Ambiente(100, 100, 100);

        // Criando um robô
        Robo robo = new Robo("Robson", 3, 22, "Norte");

        // Movendo o robô
        robo.mover(2,33);

        // Exibindo a posição
        robo.exibirPosicao();

        // Verificando se está dentro do ambiente
        if (ambiente.dentroDosLimites(robo.retornarX(),robo.retornarY())) {
            System.out.println("O robô está dentro dos limites do ambiente.");
        } else {
            System.out.println("O robô está fora dos limites do ambiente.");
        }
    }
}
