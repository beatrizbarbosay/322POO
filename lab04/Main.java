import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando o ambiente tridimensional
       Ambiente ambiente = new Ambiente(30, 30, 30);

        // Adicionando os minerais ao ambeinte em suas posições específicas
       ambiente.adicionarMineral(11, 16, "Ametista");
       ambiente.adicionarMineral(15, 21, "Ferro");
       ambiente.adicionarMineral(30, 30, "Esmeralda");

       // Adicionando obstáculos ao ambiente
        Obstaculo o1 = new Obstaculo(2, 3, 0, TipoObstaculo.Fogo);
        ambiente.adicionarEntidade(o1);

        Obstaculo o2 = new Obstaculo(15, 15, 0, TipoObstaculo.Cachoeira);
        ambiente.adicionarEntidade(o2);

        Obstaculo o3 = new Obstaculo(5, 11, 0, TipoObstaculo.Arvore);
        ambiente.adicionarEntidade(o3);

        Obstaculo o4 = new Obstaculo(22, 27, 0, TipoObstaculo.Parede);
        ambiente.adicionarEntidade(o4);

        // Adicionando os robôs ao ambiente

        // Criando robô terrestre com seu nome, coordenada X e Y, direção e velocidade máxima
        RoboTerrestre robo1 = new RoboTerrestre("Robson", 3, 22, "Norte", 15);
        ambiente.adicionarEntidade(robo1);
        // Acoplando sensores de temperatura e de proximidade ao robô e definindo seus raios
        SensorQuenteFrio sensor1 = new SensorQuenteFrio(10);
        robo1.adicionarSensor(sensor1);
        robo1.adicionarSensor(new SensorProximidade(8));

        // Criando robô bombardeiro com seu nome, coordenada X e Y, direção, velocidade máxima e quantidade de bombas
        RoboBombardeiro robo2 = new RoboBombardeiro("Roberta", 5, 5, "Sul", 10, 2);
        ambiente.adicionarEntidade(robo2);
        // Acoplando sensor de proximidade ao robô e definindo seu raio
        SensorProximidade sensor2 = new SensorProximidade(15);
        robo2.adicionarSensor(sensor2);

        // Criando robô minerador com seu nome, coordenada X e Y, direção, velocidade máxima e tipod de mineral
        RoboMinerador robo3 = new RoboMinerador("Ronaldo", 10, 15, "Leste", 13, "Ferro", ambiente);
        ambiente.adicionarEntidade(robo3);
        // Acoplando sensores de proximidade e temperatura ao robô e definindo seus raios
        SensorProximidade sensor3 = new SensorProximidade(5);
        robo3.adicionarSensor(sensor3);
        robo3.adicionarSensor(new SensorQuenteFrio(10));

        // Criando robô aéreo com seu nome, coordenada X e Y, direção, altitude e altitude máxima
        RoboAereo robo4 = new RoboAereo("Airton", 12, 9, "Oeste", 0, 30);
        ambiente.adicionarEntidade(robo4);
        // Acoplando sensor de temperatura ao robô e definindo seu raio
        SensorQuenteFrio sensor4 = new SensorQuenteFrio(30);
        robo4.adicionarSensor(sensor4);

        // Criando robô aéreo fantasma com seu nome, coordenada X e Y, direção, altitude e altitude máxima
        RoboAereoFantasma robo5 = new RoboAereoFantasma("Gasparzinho", 1, 27, "Sul", 25, 30);
        ambiente.adicionarEntidade(robo5);
        // Acoplando sensor de temperatura ao robô e definindo seu raio
        SensorQuenteFrio sensor5 = new SensorQuenteFrio(12);
        robo5.adicionarSensor(sensor5);

        // Criando robô aéreo fada com seu nome, coordenada X e Y, direção, altitude, altitude máxima e cor atual
        RoboAereoFada robo6 = new RoboAereoFada("Oyara", 13, 13, "Norte", 12, 30, "Rosa");
        ambiente.adicionarEntidade(robo6);
        // Acoplando sensores de proximidade e temperatura ao robô e definindo seus raios
        SensorProximidade sensor6 = new SensorProximidade(6);
        robo6.adicionarSensor(sensor6);
        robo6.adicionarSensor(new SensorQuenteFrio(20));



        System.out.println("");
        System.out.println("CONTROLE DE ROBÔS:");
        System.out.println("");

        // Verifica se há robôs cadastrados
        boolean haRobo = false;
        for (Entidade e : ambiente.getEntidades()) {
            if (e instanceof Robo) {
                haRobo = true;
                break;
            }
        }
        if (!haRobo) {
            System.out.println("Nenhum robô foi adicionado.");
        }
        // Lista os robôs disponíveis, seu tipo e os sensores acoplados
        System.out.println("Robôs disponíveis:");
        for (Entidade e : ambiente.getEntidades()) {
            if (e instanceof Robo) {
                Robo r = (Robo) e;
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

                System.out.println("- " + r.retornarNome() + " " + tipo + " em (" + r.retornarX() + ", " + r.retornarY() + ", " + r.retornarZ() + ")" + " com sensores: " + sensorInfo);
            }
        }
        System.out.println("");

        ambiente.visualizarAmbiente();
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
            Robo robo = null;
            for (Entidade e : ambiente.getEntidades()) {
                if (e instanceof Robo && ((Robo) e).retornarNome().equalsIgnoreCase(nomeControle)) {
                    robo = (Robo) e;
                    ambiente.visualizarAmbienteComDestaque(robo);
                    break;
                }
            }

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
                    case 1: {
                        System.out.print("Quanto irá mover em X: ");
                        int mx = scanner.nextInt();
                        System.out.print("Quanto irá mover em Y: ");
                        int my = scanner.nextInt();
                        scanner.nextLine();
                        try {
                            robo.mover(mx, my, ambiente);
                            robo.exibirPosicao();
                            ambiente.visualizarAmbienteComDestaque(robo);
                        } catch (ColisaoException | ForaDosLimitesException e) {
                            System.out.println("Erro ao mover robô: " + e.getMessage());
                        }
                        robo.exibirPosicao();
                        ambiente.visualizarAmbienteComDestaque(robo);

                        int altitude = (robo instanceof RoboAereo) ? ((RoboAereo) robo).getAltitude() : 0;

                        break;
                    }
                    case 2: {
                        if (robo instanceof RoboAereo) {
                            System.out.print("Valor para subir: ");
                            int subir = scanner.nextInt();
                            scanner.nextLine();

                            ((RoboAereo) robo).subir(subir);
                            robo.exibirPosicao();

                        } else {
                            System.out.println("Comando inválido para este robô.");
                        }
                        break;
                    }
                    case 3: {
                        if (robo instanceof RoboAereo) {
                            System.out.print("Valor para descer: ");
                            int descer = scanner.nextInt();
                            scanner.nextLine();

                            ((RoboAereo) robo).descer(descer);
                            robo.exibirPosicao();

                        } else {
                            System.out.println("Comando inválido para este robô.");
                        }
                        break;
                    }
                    case 4: {
                        if (robo instanceof RoboBombardeiro) {
                            ((RoboBombardeiro) robo).deixarBomba();
                        } else {
                            System.out.println("Comando inválido para este robô.");
                        }
                        break;
                    }
                    case 5: {
                        if (robo instanceof RoboMinerador) {
                            ((RoboMinerador) robo).minerar();
                        } else {
                            System.out.println("Comando inválido para este robô.");
                        }
                        break;
                    }
                    case 6: {
                        if (robo instanceof RoboAereoFada) {
                            System.out.print("Nova cor: ");
                            String cor = scanner.nextLine();
                            ((RoboAereoFada) robo).mudarCor(cor);
                        } else {
                            System.out.println("Comando inválido para este robô.");
                        }
                        break;
                    }
                    case 7: {
                        if (robo instanceof RoboAereoFada) {
                            ((RoboAereoFada) robo).brilhar();
                        } else {
                            System.out.println("Comando inválido para este robô.");
                        }
                        break;
                    }
                    case 8: {
                        if (robo instanceof RoboAereoFantasma) {
                            System.out.print("Nome do robô a verificar como obstáculo: ");
                            String nomeObs = scanner.nextLine();

                            Robo roboObs = null;
                            for (Entidade e : ambiente.getEntidades()) {
                                if (e instanceof Robo && ((Robo) e).retornarNome().equalsIgnoreCase(nomeObs)) {
                                    roboObs = (Robo) e;
                                    break;
                                }
                            }
                            if (roboObs == null) {
                                System.out.println("Robô não encontrado.");
                            } else {
                                ((RoboAereoFantasma) robo).identificarObstaculo(roboObs);
                            }
                        } else {
                            System.out.println("Comando inválido para este robô.");
                        }
                        break;
                    }
                    case 9: {
                        if (!robo.getSensores().isEmpty()) {
                            System.out.println("Sensores do robô:");
                            for (int i = 0; i < robo.getSensores().size(); i++) {
                                System.out.println((i + 1) + ". " + robo.getSensores().get(i).getTipoSensor());
                            }
                            System.out.print("Escolha o sensor para monitorar: ");
                            int idxSensor = scanner.nextInt();
                            scanner.nextLine();

                            if (idxSensor < 1 || idxSensor > robo.getSensores().size()) {
                                System.out.println("Sensor inválido.");
                            } else {
                                Sensor sensor = robo.getSensores().get(idxSensor - 1);
                                sensor.monitorar(robo, ambiente);
                            }
                        } else {
                            System.out.println("Este robô não possui sensores.");
                        }
                        break;
                    }
                    default:
                        System.out.println("Comando inválido.");
                }
        }

        System.out.println("Programa finalizado.");
        scanner.close();
    }
}