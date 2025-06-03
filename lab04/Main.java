import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando o ambiente tridimentsional
       Ambiente ambiente = new Ambiene(100, 100, 100);

        // Adicionando os minerais ao ambeinte em suas posições específicas
       ambiente.adicionarMineral(11, 16, "Ametista");
       ambiente.adicionarMineral(15, 21, "Ferro");
       ambiente.adicionarMineral(30, 30, "Esmeralda");

       // Adicionando obstáculos ao ambiente
        Obstaculo o1 = new Obstaculo(2, 3, 0, TipoObstaculo.Fogo);
        ambiente.adicionarEntidade(o1);

        Obstaculo o2 = new Obstaculo(15, 15, 0, TipoObstaculo.Cachoeira);
        ambiente.adicionarEntidade(o2);

        Obstaculo o3 = new Obstaculo(30, 25, 0, TipoObstaculo.Arvore);
        ambiente.adicionarEntidade(o3);

        Obstaculo o4 = new Obstaculo(50, 55, 0, TipoObstaculo.Parede);
        ambiente.adicionarEntidade(o4);

        // Adicionando os robôs ao ambiente

        // Criando robô terrestre com seu nome, coordenada X e Y, direção e velocidade máxima
        RoboTerrestre robo1 = new RoboTerrestre("Robson", 3, 22, "Norte", 10);
        ambiente.adicionarEntidade(robo1);
        // Acoplando sensores de temperatura e de proximidade ao robô e definindo seus raios
        SensorQuenteFrio sensor1 = new SensorQuenteFrio(10);
        robo1.adicionarSensor(sensor1);
        robo1.adicionarSensor(new SensorProximidade(8));

        // Criando robô bombardeiro com seu nome, coordenada X e Y, direção, velocidade máxima e quantidade de bombas
        RoboBombardeiro robo2 = new RoboBombardeiro("Roberta", 5, 5, "Sul", 7, 2);
        ambiente.adicionarEntidade(robo2);
        // Acoplando sensor de proximidade ao robô e definindo seu raio
        SensorProximidade sensor2 = new SensorProximidade(15);
        robo2.adicionarSensor(sensor2);

        // Criando robô minerador com seu nome, coordenada X e Y, direção, velocidade máxima e tipod de mineral
        RoboMinerador robo3 = new RoboMinerador("Ronaldo", 10, 15, "Leste", 6, "Ferro", ambiente);
        ambiente.adicionarEntidade(robo3);
        // Acoplando sensores de proximidade e temperatura ao robô e definindo seus raios
        SensorProximidade sensor3 = new SensorProximidade(5);
        robo3.adicionarSensor(sensor3);
        robo3.adicionarSensor(new SensorQuenteFrio(10));

        // Criando robô aéreo com seu nome, coordenada X e Y, direção, altitude e altitude máxima
        RoboAereo robo4 = new RoboAereo("Airton", 30, 30, "Oeste", 10, 80);
        ambiente.adicionarEntidade(robo4);
        // Acoplando sensor de temperatura ao robô e definindo seu raio
        SensorQuenteFrio sensor4 = new SensorQuenteFrio(30);
        robo4.adicionarSensor(sensor4);

        // Criando robô aéreo fantasma com seu nome, coordenada X e Y, direção, altitude e altitude máxima
        RoboAereoFantasma robo5 = new RoboAereoFantasma("Gasparzinho", 52, 53, "Sul", 25, 50);
        ambiente.adicionarEntidade(robo5);
        // Acoplando sensor de temperatura ao robô e definindo seu raio
        SensorQuenteFrio sensor5 = new SensorQuenteFrio(12);
        robo5.adicionarSensor(sensor5);

        // Criando robô aéreo fada com seu nome, coordenada X e Y, direção, altitude, altitude máxima e cor atual
        RoboAereoFada robo6 = new RoboAereoFada("Oyara", 70, 70, "Norte", 30, 100, "Rosa");
        ambiente.adicionarEntidade(robo6);
        // Acoplando sensores de proximidade e temperatura ao robô e definindo seus raios
        SensorProximidade sensor6 = new SensorProximidade(6);
        robo6.adicionarSensor(sensor6);
        robo6.adicionarSensor(new SensorQuenteFrio(20));



    
        System.out.println("CONTROLE DE ROBÔS:");

        // Verifica se há robôs cadastrados
        if (ambiente.getRobos().isEmpty()) {
            System.out.println("Nenhum robô foi adicionado.");
        }
        
        // Lista os robôs disponíveis, seu tipo e os sensores acoplados
        System.out.println("Robôs disponíveis:");
        for (Robo r : ambiente.getRobos()) {
            String tipo = "";
            if (r instanceof RoboBombardeiro) {
                tipo = "(robô bombardeiro)";
            } else if (r instanceof RoboMinerador) {
                tipo = "(robô minerador)";
            } else if (r instanceof RoboTerrestre) {
                tipo = "(robô terrestre)";
            } else if (r instanceof RoboAereoFada) {
                tipo = "(robô aéreo fada)";
            } else if (r instanceof RoboAereoFantasma) {
                tipo = "(robô aéreo fantasma)";
            } else if (r instanceof RoboAereo) {
                tipo = "(robô aéreo)";
            }
            String sensorInfo = "";
            if (!r.getSensores().isEmpty()) {
                for (int i = 0; i < r.getSensores().size(); i++) {
                    Sensor s = r.getSensores().get(i);
                    sensorInfo += s.getTipoSensor();

                    if (i < r.getSensores().size() - 1) {
                        sensorInfo += ", ";
                    }
                }
            } else {
                sensorInfo = "Nenhum sensor";
            }

            System.out.println("- " + r.retornarNome() + " " + tipo + " com " + sensorInfo);
        }
        System.out.println("");

        // Loop para interação com o usuário
        while (true) {
            // Seleção do robô para controle
            System.out.print("Digite o nome do robô para controlar (ou 'sair' para encerrar): ");
            String nomeControle = scanner.nextLine();
            if (nomeControle.equalsIgnoreCase("sair")) {
                break;
            }

            // Busca o robô pelo nome
            Robo robo = ambiente.getRoboPorNome(nomeControle);
            if (robo == null) {
                System.out.println("Robô não encontrado.");
                continue;
            }

            // Exibe os comandos possíveis com base no tipo de robô
            System.out.println("Comandos disponíveis:");
            System.out.println("1. Mover");
            System.out.println("9. Monitorar Sensor");

            if (robo instanceof RoboAereo) {
                System.out.println("2. Subir");
                System.out.println("3. Descer");
            }
            if (robo instanceof RoboBombardeiro) {
                System.out.println("4. Deixar bomba");
            }
            if (robo instanceof RoboMinerador) {
                System.out.println("5. Minerar");
            }
            if (robo instanceof RoboAereoFada) {
                System.out.println("6. Mudar cor");
                System.out.println("7. Brilhar");
            }
            if (robo instanceof RoboAereoFantasma) {
                System.out.println("8. Identificar obstáculo");
            }

            // Executa o comando escolhido
            System.out.print("Escolha o comando: ");
            int cmd = scanner.nextInt();
            scanner.nextLine();

            switch (cmd) {
                case 1:
                    // Move o robô no eixo (X,Y) e exibe sua posição final
                    System.out.print("Quanto irá mover em X: ");
                    int mx = scanner.nextInt();
                    System.out.print("Quanto irá mover em Y: ");
                    int my = scanner.nextInt();
                    robo.mover(mx, my);
                    robo.exibirPosicao();
                    if (!ambiente.dentroDosLimites(robo.retornarX(), robo.retornarY(), 0)) {
                        System.out.print("Robô está fora dos limites");
                        break;
                    }                
                    break;
                case 2:
                    // Move o robô para cima no eixo Z (subida) e exibe sua posição final
                    if (robo instanceof RoboAereo) {
                        System.out.print("Valor para subir: ");
                        int subir = scanner.nextInt();
                        ((RoboAereo) robo).subir(subir);
                        robo.exibirPosicao();
                        if (!ambiente.dentroDosLimites(robo.retornarX(), robo.retornarY(), ((RoboAereo) robo).getAltitude())) {
                        System.out.print("Robô está fora dos limites");
                        break;
                        }          
                    }
                    break;
                case 3:
                    // Move o robô para baixo no eixo Z (descida) e exibe sua posição final
                    if (robo instanceof RoboAereo) {
                        System.out.print("Valor para descer: ");
                        int descer = scanner.nextInt();
                        ((RoboAereo) robo).descer(descer);
                        robo.exibirPosicao();
                        if (!ambiente.dentroDosLimites(robo.retornarX(), robo.retornarY(), ((RoboAereo) robo).getAltitude())) {
                        System.out.println("Robô está fora dos limites");
                        break;
                    }
                    break;
                    }
                case 4:
                    // Robô deixa uma bomba em sua coordenada
                    if (robo instanceof RoboBombardeiro) {
                        ((RoboBombardeiro) robo).deixarBomba();
                    }
                    break;
                case 5:
                    //Robô tenta minerar, consegue minerar caso haja um mineral em sua coordenada
                    if (robo instanceof RoboMinerador) {
                        ((RoboMinerador) robo).minerar();
                    }
                    break;
                case 6:
                    //Muda a cor do robô aéreo fada
                    if (robo instanceof RoboAereoFada) {
                        System.out.print("Nova cor: ");
                        String cor = scanner.nextLine();
                        ((RoboAereoFada) robo).mudarCor(cor);
                    }
                    break;
                    //Robô aéreo fada brilha em sua cor atual
                case 7:
                    if (robo instanceof RoboAereoFada) {
                        ((RoboAereoFada) robo).brilhar();
                    }
                    break;
                case 8:
                    //Verifica que o robô fantasma não identifica nenhum obstáculo (pois ele ultrapassa os obstáculos)
                    if (robo instanceof RoboAereoFantasma) {
                        System.out.print("Nome do robô a verificar como obstáculo: ");
                        String nomeObs = scanner.nextLine();
                        Robo alvo = ambiente.getRoboPorNome(nomeObs);
                        if (alvo != null) {
                            ((RoboAereoFantasma) robo).identificarObstaculo(alvo);
                        } else {
                            System.out.println("Robô não encontrado.");
                        }
                    }
                    break;
                case 9:
                    // Sensor é ativado
                    robo.acionarSensor(ambiente);
            }
        }

    System.out.println("Encerrando...");
    scanner.close();
    }
}