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
        RoboAereo robo4 = new RoboAereo("Airton", 30, 30, "Oeste",10,100); //cria o robo aereo e testa seus comandos
        ambiente.adicionarRobo(robo4);
        robo4.subir(10);
        robo4.mover(2,2);
        robo4.exibirPosicao();
        robo4.descer(5);
        robo4.exibirPosicao();

        RoboAereoFantasma robo5 = new RoboAereoFantasma("Gasparzinho", 50, 50, "Sul", 20, 100); //cria o robo aereo fantasma e testa seus comandos
        ambiente.adicionarRobo(robo5);
        robo5.mover(3,3);
        robo5.exibirPosicao();
        robo5.identificarObstaculo(robo1);


        RoboAereoFada robo6 = new RoboAereoFada("Tinker Bell", 70, 70, "Norte", 30, 100, "Rosa"); //cria o robo aereo fada e testa seus comandos
        robo6.exibirPosicao();
        ambiente.adicionarRobo(robo6);
        robo6.mudarCor("Azul");
        robo6.brilhar();
        robo6.mover(4,4);
        robo6.exibirPosicao();




    }
}
