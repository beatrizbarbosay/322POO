import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Criando o ambiente
       Ambiente ambiente = new Ambiente(100, 100, 100);

        // Adicionando os minerais
       ambiente.adicionarMineral(11, 16, "Ametista");
       ambiente.adicionarMineral(15, 21, "Ferro");
       ambiente.adicionarMineral(30, 30, "Esmeralda");

       Obstaculo o1 = new Obstaculo(2, 3, 4, 3, TipoObstaculo.Fogo);
        ambiente.adicionarObstaculo(o1);

        Obstaculo o2 = new Obstaculo(6, 6, 6, 8, TipoObstaculo.Cachoeira);
        ambiente.adicionarObstaculo(o2);

        while (true) {
            System.out.println("Deseja adicionar robô?");
            System.out.println("1. Sim");
            System.out.println("2. Não");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            if (opcao == 1) {
                System.out.println("Tipo de robô:");
                System.out.println("1. Terrestre");
                System.out.println("2. Aéreo");
                System.out.print("Escolha: ");
                int tipo = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Nome do robô: ");
                String nome = scanner.nextLine();
                System.out.print("Coordenada X: ");
                int x = scanner.nextInt();
                System.out.print("Coordenada Y: ");
                int y = scanner.nextInt();
                scanner.nextLine(); // limpar buffer
                System.out.print("Direção: ");
                String direcao = scanner.nextLine();
                System.out.println("Tipo de sensor:");
                System.out.println("1. Temperatura");
                System.out.println("2. Proximidade");
                System.out.print("Escolha: ");
                int SensorTipo = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Raio do sensor: ");
                double raio = scanner.nextDouble();
        
               





                if (tipo == 1) {
                    System.out.print("Velocidade do Robô: ");
                    int velocidade = scanner.nextInt();
                    scanner.nextLine(); // limpar buffer

                    System.out.print("Deseja adicionar um complemento? (s/n): ");
                    String resposta = scanner.nextLine();

                    if (resposta.equalsIgnoreCase("s")) {
                        System.out.println("Escolha o complemento:");
                        System.out.println("1. Minerador");
                        System.out.println("2. Bombardeiro");
                        int comp = scanner.nextInt();
                        scanner.nextLine();

                        if (comp == 1) {
                            System.out.print("Tipo de mineral: ");
                            String tipoMineral = scanner.nextLine();
                            RoboMinerador robo = new RoboMinerador(nome, x, y, direcao, velocidade, tipoMineral, ambiente);
                            ambiente.adicionarRobo(robo);
                            System.out.println("Robô minerador adicionado!");
                            if (SensorTipo == 1){
                                SensorQuenteFrio sensor = new SensorQuenteFrio(raio);
                                robo.adicionarSensor(sensor);
                            } else if (SensorTipo == 2){
                                SensorProximidade sensor = new SensorProximidade(raio);
                                robo.adicionarSensor(sensor);
                            }
                        } else if (comp == 2) {
                            System.out.print("Quantidade de bombas: ");
                            int quantidadeBombas = scanner.nextInt();
                            RoboBombardeiro robo = new RoboBombardeiro(nome, x, y, direcao, velocidade, quantidadeBombas);
                            ambiente.adicionarRobo(robo);
                            System.out.println("Robô bombardeiro adicionado!");
                            if (SensorTipo == 1){
                                SensorQuenteFrio sensor = new SensorQuenteFrio(raio);
                                robo.adicionarSensor(sensor);
                            } else if (SensorTipo == 2){
                                SensorProximidade sensor = new SensorProximidade(raio);
                                robo.adicionarSensor(sensor);
                            }
                        }
                    } else {
                        RoboTerrestre robo = new RoboTerrestre(nome, x, y, direcao, velocidade);
                        ambiente.adicionarRobo(robo);
                        System.out.println("Robô terrestre adicionado!");
                        if (SensorTipo == 1){
                                SensorQuenteFrio sensor = new SensorQuenteFrio(raio);
                                robo.adicionarSensor(sensor);
                            } else if (SensorTipo == 2){
                                SensorProximidade sensor = new SensorProximidade(raio);
                                robo.adicionarSensor(sensor);
                            }
                    }
                } else if (tipo == 2) {
                    System.out.print("Altitude: ");
                    int altitude = scanner.nextInt();
                    System.out.print("Altitude máxima: ");
                    int altitudeMaxima = scanner.nextInt();
                    scanner.nextLine(); // limpar buffer

                    System.out.print("Deseja adicionar um complemento? (s/n): ");
                    String resposta = scanner.nextLine();

                    if (resposta.equalsIgnoreCase("s")) {
                        System.out.println("Escolha o complemento:");
                        System.out.println("1. Fada");
                        System.out.println("2. Fantasma");
                        int comp = scanner.nextInt();
                        scanner.nextLine();

                        if (comp == 1) {
                            System.out.print("Cor: ");
                            String cor = scanner.nextLine();
                            RoboAereoFada robo = new RoboAereoFada(nome, x, y, direcao, altitude, altitudeMaxima, cor);
                            ambiente.adicionarRobo(robo);
                            System.out.println("Robô aéreo fada adicionado!");
                            if (SensorTipo == 1){
                                SensorQuenteFrio sensor = new SensorQuenteFrio(raio);
                                robo.adicionarSensor(sensor);
                            } else if (SensorTipo == 2){
                                SensorProximidade sensor = new SensorProximidade(raio);
                                robo.adicionarSensor(sensor);
                            }
                        } else if (comp == 2) {
                            RoboAereoFantasma robo = new RoboAereoFantasma(nome, x, y, direcao, altitude, altitudeMaxima);
                            ambiente.adicionarRobo(robo);
                            System.out.println("Robô aéreo fantasma adicionado!");
                            if (SensorTipo == 1){
                                SensorQuenteFrio sensor = new SensorQuenteFrio(raio);
                                robo.adicionarSensor(sensor);
                            } else if (SensorTipo == 2){
                                SensorProximidade sensor = new SensorProximidade(raio);
                                robo.adicionarSensor(sensor);
                            }
                        }
                    } else {
                        RoboAereo robo = new RoboAereo(nome, x, y, direcao, altitude, altitudeMaxima);
                        ambiente.adicionarRobo(robo);
                        System.out.println("Robô aéreo adicionado!");
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

            } else if (opcao == 2) {
    while (true) {
        System.out.println("\n--- CONTROLE DE ROBÔS ---");
        if (ambiente.getRobos().isEmpty()) {
            System.out.println("Nenhum robô foi adicionado.");
            break;
        }

        System.out.println("Robôs disponíveis:");
        for (Robo r : ambiente.getRobos()) {
            System.out.println("- " + r.retornarNome());
        }

        System.out.print("Digite o nome do robô para controlar (ou 'sair' para encerrar): ");
        String nomeControle = scanner.nextLine();
        if (nomeControle.equalsIgnoreCase("sair")) {
            break;
        }

        Robo robo = ambiente.getRoboPorNome(nomeControle);
        if (robo == null) {
            System.out.println("Robô não encontrado.");
            continue;
        }

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

        System.out.print("Escolha o comando: ");
        int cmd = scanner.nextInt();
        scanner.nextLine();

        switch (cmd) {
            case 1:
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
                if (robo instanceof RoboBombardeiro) {
                    ((RoboBombardeiro) robo).deixarBomba();
                }
                break;
            case 5:
                if (robo instanceof RoboMinerador) {
                    ((RoboMinerador) robo).minerar();
                }
                break;
            case 6:
                if (robo instanceof RoboAereoFada) {
                    System.out.print("Nova cor: ");
                    String cor = scanner.nextLine();
                    ((RoboAereoFada) robo).mudarCor(cor);
                }
                break;
            case 7:
                if (robo instanceof RoboAereoFada) {
                    ((RoboAereoFada) robo).brilhar();
                }
                break;
            case 8:
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
                robo.acionarSensor(ambiente);
        }
    }

    System.out.println("Encerrando...");
    break;
} else {
                System.out.println("Opção inválida!");
            }

            System.out.println(); // espaço entre interações
        }

        scanner.close();
    }
}