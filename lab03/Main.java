import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando o ambiente tridimensional
       Ambiente ambiente = new Ambiente(100, 100, 100);

        // Adicionando os minerais ao ambeinte em suas posições específicas
       ambiente.adicionarMineral(11, 16, "Ametista");
       ambiente.adicionarMineral(15, 21, "Ferro");
       ambiente.adicionarMineral(30, 30, "Esmeralda");

       // Adicionando obstáculos ao ambiente
        Obstaculo o1 = new Obstaculo(2, 3, 4, 3, TipoObstaculo.Fogo);
        ambiente.adicionarObstaculo(o1);

        Obstaculo o2 = new Obstaculo(6, 6, 6, 8, TipoObstaculo.Cachoeira);
        ambiente.adicionarObstaculo(o2);

        // Loop principal para interação com o usuário
        while (true) {
            System.out.println("Deseja adicionar robô?");
            System.out.println("1. Sim");
            System.out.println("2. Não");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            if (opcao == 1) {
                // Entrada de dados do robô
                System.out.println("Tipo de robô:");
                System.out.println("1. Terrestre");
                System.out.println("2. Aéreo");
                System.out.print("Escolha: ");
                int tipo = scanner.nextInt();
                scanner.nextLine();

                // Coleta de informações que todos os robôs possuem
                System.out.print("Nome do robô: ");
                String nome = scanner.nextLine();
                System.out.print("Coordenada X: ");
                int x = scanner.nextInt();
                System.out.print("Coordenada Y: ");
                int y = scanner.nextInt();
                scanner.nextLine(); // limpar buffer
                System.out.print("Direção: ");
                String direcao = scanner.nextLine();

                // Escolha do sensor acoplado ao robô
                System.out.println("Tipo de sensor:");
                System.out.println("1. Temperatura");
                System.out.println("2. Proximidade");
                System.out.print("Escolha: ");
                int SensorTipo = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Raio do sensor: ");
                double raio = scanner.nextDouble();
        
               




                // Criação do robô caso seja terrestre
                if (tipo == 1) {
                    System.out.print("Velocidade do Robô: ");
                    int velocidade = scanner.nextInt();
                    scanner.nextLine(); // limpar buffer

                    System.out.print("Deseja adicionar um complemento? (s/n): ");
                    String resposta = scanner.nextLine();

                    // Escolha do complemento do robô terrestre (se houver)
                    if (resposta.equalsIgnoreCase("s")) {
                        System.out.println("Escolha o complemento:");
                        System.out.println("1. Minerador");
                        System.out.println("2. Bombardeiro");
                        int comp = scanner.nextInt();
                        scanner.nextLine();

                        if (comp == 1) {
                            // Cria um robô terrestre minerador
                            System.out.print("Tipo de mineral: ");
                            String tipoMineral = scanner.nextLine();
                            RoboMinerador robo = new RoboMinerador(nome, x, y, direcao, velocidade, tipoMineral, ambiente);
                            ambiente.adicionarRobo(robo);
                            System.out.println("Robô minerador adicionado!");
                            // Adiciona o sensor escolhido
                            if (SensorTipo == 1){
                                SensorQuenteFrio sensor = new SensorQuenteFrio(raio);
                                robo.adicionarSensor(sensor);
                            } else if (SensorTipo == 2){
                                SensorProximidade sensor = new SensorProximidade(raio);
                                robo.adicionarSensor(sensor);
                            }
                        } else if (comp == 2) {
                            // Cria um robô terrestre bombardeiro
                            System.out.print("Quantidade de bombas: ");
                            int quantidadeBombas = scanner.nextInt();
                            RoboBombardeiro robo = new RoboBombardeiro(nome, x, y, direcao, velocidade, quantidadeBombas);
                            ambiente.adicionarRobo(robo);
                            System.out.println("Robô bombardeiro adicionado!");
                            // Adiciona o sensor escolhido
                            if (SensorTipo == 1){
                                SensorQuenteFrio sensor = new SensorQuenteFrio(raio);
                                robo.adicionarSensor(sensor);
                            } else if (SensorTipo == 2){
                                SensorProximidade sensor = new SensorProximidade(raio);
                                robo.adicionarSensor(sensor);
                            }
                        }
                    } else {
                        // Cria um robô terrestre (sem complemento)
                        RoboTerrestre robo = new RoboTerrestre(nome, x, y, direcao, velocidade);
                        ambiente.adicionarRobo(robo);
                        System.out.println("Robô terrestre adicionado!");
                        // Adiciona o sensor escolhido
                        if (SensorTipo == 1){
                                SensorQuenteFrio sensor = new SensorQuenteFrio(raio);
                                robo.adicionarSensor(sensor);
                            } else if (SensorTipo == 2){
                                SensorProximidade sensor = new SensorProximidade(raio);
                                robo.adicionarSensor(sensor);
                            }
                    }
                } else if (tipo == 2) {
                    // Criação do robô caso seja aéreo
                    System.out.print("Altitude: ");
                    int altitude = scanner.nextInt();
                    System.out.print("Altitude máxima: ");
                    int altitudeMaxima = scanner.nextInt();
                    scanner.nextLine(); // limpar buffer

                    System.out.print("Deseja adicionar um complemento? (s/n): ");
                    String resposta = scanner.nextLine();

                    // Escolha do complemento do robô terrestre (se houver)
                    if (resposta.equalsIgnoreCase("s")) {
                        System.out.println("Escolha o complemento:");
                        System.out.println("1. Fada");
                        System.out.println("2. Fantasma");
                        int comp = scanner.nextInt();
                        scanner.nextLine();

                        // Cria um robô aéreo fada
                        if (comp == 1) {
                            System.out.print("Cor: ");
                            String cor = scanner.nextLine();
                            RoboAereoFada robo = new RoboAereoFada(nome, x, y, direcao, altitude, altitudeMaxima, cor);
                            ambiente.adicionarRobo(robo);
                            System.out.println("Robô aéreo fada adicionado!");
                            // Adiciona o sensor escolhido
                            if (SensorTipo == 1){
                                SensorQuenteFrio sensor = new SensorQuenteFrio(raio);
                                robo.adicionarSensor(sensor);
                            } else if (SensorTipo == 2){
                                SensorProximidade sensor = new SensorProximidade(raio);
                                robo.adicionarSensor(sensor);
                            }

                        // Cria um robô aéreo fantasma
                        } else if (comp == 2) {
                            RoboAereoFantasma robo = new RoboAereoFantasma(nome, x, y, direcao, altitude, altitudeMaxima);
                            ambiente.adicionarRobo(robo);
                            System.out.println("Robô aéreo fantasma adicionado!");
                            // Adiciona o sensor escolhido
                            if (SensorTipo == 1){
                                SensorQuenteFrio sensor = new SensorQuenteFrio(raio);
                                robo.adicionarSensor(sensor);
                            } else if (SensorTipo == 2){
                                SensorProximidade sensor = new SensorProximidade(raio);
                                robo.adicionarSensor(sensor);
                            }
                        }
                    // Cria um robô terrestre (sem complemento)
                    } else {
                        RoboAereo robo = new RoboAereo(nome, x, y, direcao, altitude, altitudeMaxima);
                        ambiente.adicionarRobo(robo);
                        System.out.println("Robô aéreo adicionado!");
                        // Adiciona o sensor escolhido
                        if (SensorTipo == 1){
                                SensorQuenteFrio sensor = new SensorQuenteFrio(raio);
                                robo.adicionarSensor(sensor);
                            } else if (SensorTipo == 2){
                                SensorProximidade sensor = new SensorProximidade(raio);
                                robo.adicionarSensor(sensor);
                            }
                    }
                } else {
                    System.out.println("Tipo inválido!");
                }

             // Caso o usuário escolha não adicionar mais robôs, inicia o controle dos existentes    
            } else if (opcao == 2) {
    while (true) {
        System.out.println("\n--- CONTROLE DE ROBÔS ---");

        // Verifica se há robôs cadastrados
        if (ambiente.getRobos().isEmpty()) {
            System.out.println("Nenhum robô foi adicionado.");
            break;
        }
        
        // Lista os robôs disponíveis
        System.out.println("Robôs disponíveis:");
        for (Robo r : ambiente.getRobos()) {
            System.out.println("- " + r.retornarNome());
        }
        
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
                    System.out.print("Robô está fora dos limites");
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
    break;  // Sai do programa
} else {
                System.out.println("Opção inválida!");
            }

            System.out.println(); // espaço entre interações
        }

        scanner.close();
    }
}