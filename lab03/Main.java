public class Main {
    public static void main(String[] args) {
        // Criando o ambiente
        Ambiente ambiente = new Ambiente(100, 100, 100);

        ambiente.adicionarMineral(11, 16, "Ametista");
        ambiente.adicionarMineral(15, 21, "Ferro");
        ambiente.adicionarMineral(30, 30, "Esmeralda");

        // Criando um robô terrestre (Robô 1)
        RoboTerrestre robo1 = new RoboTerrestre("Robson", 3, 22, "Norte", 10);
        ambiente.adicionarRobo(robo1);

        // Movendo o robô
        robo1.mover(2,9); // Deve se mover para a posição nova, pois não move uma distância maior que sua velocidade máxima (10)

        // Exibindo a posição do robô
        robo1.exibirPosicao();

        // Verificando se está dentro do ambiente
        if (ambiente.dentroDosLimites(robo1.retornarX(),robo1.retornarY(), 0)) {
            System.out.println("O robô está dentro dos limites do ambiente.");
        } else {
            System.out.println("O robô está fora dos limites do ambiente.");
        }

        // Saída apenas para separar os robôs
        System.out.println("-----------------------------------------");

        // Criando um robô bombardeiro (Robô 2)

        RoboBombardeiro robo2 = new RoboBombardeiro("Roberta", 5, 5, "Sul", 7, 2);
        ambiente.adicionarRobo(robo2);

        // Movendo o robô
        robo2.mover(3,3); // Deve se mover para a posição nova, pois não move uma distância maior que sua velocidade máxima (7)

        // Exibindo a posição
        robo2.exibirPosicao();

        //Deve deixar uma bomba em sua nova coordenada, pois possui 2 bombas
        robo2.deixarBomba();

        // Verificando se está dentro do ambiente
        if (ambiente.dentroDosLimites(robo2.retornarX(),robo2.retornarY(), 0)) {
            System.out.println("O robô está dentro dos limites do ambiente.");
        } else {
            System.out.println("O robô está fora dos limites do ambiente.");
        }

        // Saída apenas para separar os robôs
        System.out.println("-----------------------------------------");

        // Criando um robô minerador (Robô 3)

        RoboMinerador robo3 = new RoboMinerador("Ronaldo", 10, 15, "Leste", 6, "Ferro", ambiente);
        ambiente.adicionarRobo(robo3);

        // Movendo o robô
        robo3.mover(1,1); // Deve se mover para a posição nova, pois não move uma distância maior que sua velocidade máxima (6)

        // Exibindo a posição
        robo3.exibirPosicao();

        // Tentando minerar
        robo3.minerar(); // Deve minerar, pois
        // Verificando se está dentro do ambiente
        if (ambiente.dentroDosLimites(robo3.retornarX(),robo3.retornarY(), 0)) {
            System.out.println("O robô está dentro dos limites do ambiente.");
        } else {
            System.out.println("O robô está fora dos limites do ambiente.");
        }

        // Movendo o robô novamente
        robo3.mover(4,5); //deve se mover para a posição nova, pois não move uma distância maior que sua velocidade máxima (6)

        // Exibindo a posição
        robo3.exibirPosicao();
        
        // Tentando minerar
        robo3.minerar(); // Deve minerar, pois encontrou ferro em sua coordenada

        // Verificando se está dentro do ambiente
        if (ambiente.dentroDosLimites(robo3.retornarX(),robo3.retornarY(), 0)) {
            System.out.println("O robô está dentro dos limites do ambiente.");
        } else {
            System.out.println("O robô está fora dos limites do ambiente.");
        }

        // Saída apenas para separar os robôs
        System.out.println("-----------------------------------------");

        // Criando um robô aéreo (Robô 4)

        RoboAereo robo4 = new RoboAereo("Airton", 30, 30, "Oeste",10,80);
        ambiente.adicionarRobo(robo4);

        //Subindo o robô
        robo4.subir(10); //Deve ir para a altitude 10 + 10 (pois não ultrapassa a altitude máxima (80))

        //Movendo o robô no eixo (X,Y)
        robo4.mover(2,2);

        // Exibindo a posição
        robo4.exibirPosicao();

        //Descendo o robô
        robo4.descer(5);
  
        // Exibindo a posição
        robo4.exibirPosicao();

        // Saída apenas para separar os robôs
        System.out.println("-----------------------------------------");

        // Criando um robô fantasma (Robô 5)

        RoboAereoFantasma robo5 = new RoboAereoFantasma("Gasparzinho", 52, 53, "Sul", 25, 50);
        ambiente.adicionarRobo(robo5);

        //Subindo o robô
        robo5.subir(30); //Deve ir para a altitude máxima (50), pois se não, iria ultrapassar
        
        //Movendo o robô no eixo (X,Y)
        robo5.mover(3,3);

        // Exibindo a posição
        robo5.exibirPosicao();

        robo5.identificarObstaculo(robo1); //Não deve identificar obstáculo nenhum, pois é um fantasma (ultrapassa os obstáculos)

        // Saída apenas para separar os robôs
        System.out.println("-----------------------------------------");

        // Criando um robô fada (Robô 6)

        RoboAereoFada robo6 = new RoboAereoFada("Oyara", 70, 70, "Norte", 30, 100, "Rosa"); //cria o robo aereo fada e testa seus comandos
        ambiente.adicionarRobo(robo6);

        // Exibindo a posição (e a cor atual)
        robo6.exibirPosicao();

        // Mudando a cor do brilho
        robo6.mudarCor("Azul");

        //Movendo a fada no eixo (X,Y)
        robo6.mover(4,4);

        //Movendo a fada no eixo Z 
        robo6.subir(5);

        // Exibindo a posição (e a cor atual)
        robo6.exibirPosicao();

        // Mudando a cor do brilho
        robo6.mudarCor("Vermelho");

        // Exibindo a cor atual do brilho
        robo6.brilhar();




    }
}