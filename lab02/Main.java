public class Main {
    public static void main(String[] args) {
        // Criando o ambiente
        Ambiente ambiente = new Ambiente(100, 100, 100);

        ambiente.adicionarMineral(11, 16, "Ametista");

        // Criando um robô terrestre
        RoboTerrestre robo1 = new RoboTerrestre("Robson", 3, 22, "Norte", 10);
        ambiente.adicionarRobo(robo1);

        // Movendo o robô
        robo1.mover(2,9);

        // Exibindo a posição
        robo1.exibirPosicao();

        // Verificando se está dentro do ambiente
        if (ambiente.dentroDosLimites(robo1.retornarX(),robo1.retornarY(), 0)) {
            System.out.println("O robô está dentro dos limites do ambiente.");
        } else {
            System.out.println("O robô está fora dos limites do ambiente.");
        }

        // Criando um robô bombardeiro

        RoboBombardeiro robo2 = new RoboBombardeiro("Roberta", 5, 5, "Sul", 7, 10);
        ambiente.adicionarRobo(robo2);

        // Movendo o robô
        robo2.mover(3,3);

        // Exibindo a posição
        robo2.exibirPosicao();

        robo2.deixarBomba();

        // Verificando se está dentro do ambiente
        if (ambiente.dentroDosLimites(robo2.retornarX(),robo2.retornarY(), 0)) {
            System.out.println("O robô está dentro dos limites do ambiente.");
        } else {
            System.out.println("O robô está fora dos limites do ambiente.");
        }

        // Criando um robô minerador

        RoboMinerador robo3 = new RoboMinerador("Ronaldo", 10, 15, "Leste", 5, "Ametista", ambiente);
        ambiente.adicionarRobo(robo3);

        // Movendo o robô
        robo3.mover(1,1);

        // Exibindo a posição
        robo3.exibirPosicao();
        // Tentando minerar
        robo3.minerar(); // Deve minerar Ferro
        robo3.minerar(); // Deve dizer que não há mais Ferro

        // Verificando se está dentro do ambiente
        if (ambiente.dentroDosLimites(robo3.retornarX(),robo3.retornarY(), 0)) {
            System.out.println("O robô está dentro dos limites do ambiente.");
        } else {
            System.out.println("O robô está fora dos limites do ambiente.");
        }
    }
}
