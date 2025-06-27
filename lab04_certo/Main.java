import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ambiente ambiente = null; 
        CentralComunicacao centralComunicacao = new CentralComunicacao();

        try {
            // Criando o ambiente tridimensional
            // (largura X, altura Y, profundidade Z) 
            ambiente = new Ambiente(20, 15, 5); 

            // Adicionando os minerais ao ambiente 
            ambiente.adicionarMineral(11, 6, 0, "Ametista"); // Adicionado Z=0
            ambiente.adicionarMineral(15, 10, 0, "Ferro");  // Adicionado Z=0
            ambiente.adicionarMineral(5, 5, 0, "Esmeralda"); // Adicionado Z=0

            // Adicionando obstáculos ao ambiente
            ambiente.adicionarEntidade(new Obstaculo(2, 3, 0, TipoObstaculo.Fogo));
            ambiente.adicionarEntidade(new Obstaculo(8, 8, 0, TipoObstaculo.Cachoeira));
            ambiente.adicionarEntidade(new Obstaculo(10, 2, 0, TipoObstaculo.Arvore));
            ambiente.adicionarEntidade(new Obstaculo(18, 13, 0, TipoObstaculo.Parede));
            ambiente.adicionarEntidade(new Obstaculo(1, 1, 1, TipoObstaculo.Vulcao)); // Obstáculo em Z=1

            // Adicionando os robôs ao ambiente
            // Construtor RoboTerrestre(nome, x, y, direcao, velMax) -> Z padrão 0
            RoboTerrestre robo1 = new RoboTerrestre("Robson", 3, 2, "Norte", 5);
            robo1.adicionarSensor(new SensorQuenteFrio(7));
            robo1.adicionarSensor(new SensorProximidade(5));
            ambiente.adicionarEntidade(robo1);

            // Construtor RoboBombardeiro(nome, x, y, direcao, velMax, bombas) -> Z padrão 0
            RoboBombardeiro robo2 = new RoboBombardeiro("Roberta", 5, 5, "Sul", 3, 3);
            robo2.adicionarSensor(new SensorProximidade(8));
            ambiente.adicionarEntidade(robo2);

            // Construtor RoboMinerador(nome, x, y, direcao, velMax, tipoMineral) -> Z padrão 0
            RoboMinerador robo3 = new RoboMinerador("Ronaldo", 10, 10, "Leste", 4, "Ferro");
            robo3.adicionarSensor(new SensorProximidade(5));
            ambiente.adicionarEntidade(robo3);

            // Construtor RoboAereo(nome, x, y, direcao, altitudeInicial, altitudeMax)
            RoboAereo robo4 = new RoboAereo("Airton", 7, 7, "Oeste", 2, 4); // Z=2, MaxZ=4
            robo4.adicionarSensor(new SensorQuenteFrio(10));
            ambiente.adicionarEntidade(robo4);

            // Construtor RoboAereoFantasma(nome, x, y, direcao, altitudeInicial, altitudeMax)
            RoboAereoFantasma robo5 = new RoboAereoFantasma("Gaspar", 1, 12, "Sul", 1, 3); // Z=1, MaxZ=3
            robo5.adicionarSensor(new SensorQuenteFrio(6));
            ambiente.adicionarEntidade(robo5);

            // Construtor RoboAereoFada(nome, x, y, direcao, altitudeInicial, altitudeMax, cor)
            RoboAereoFada robo6 = new RoboAereoFada("Oyara", 17, 2, "Norte", 2, 4, "Rosa"); // Z=2, MaxZ=4
            robo6.adicionarSensor(new SensorProximidade(4));
            ambiente.adicionarEntidade(robo6);

            System.out.println("CONTROLE DE ROBÔS INICIALIZADO");
            ambiente.visualizarAmbiente(); // Visualiza camada Z=0 por padrão

        } catch (ForaDosLimitesException | ColisaoException e) {
            System.err.println("Erro Crítico na inicialização do ambiente ou entidades: " + e.getMessage());
            if (ambiente != null) ambiente.visualizarAmbiente();
            e.printStackTrace();
            return; 
        }

        Robo roboSelecionado = null;
        String nomeControle;

        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            if (roboSelecionado != null) {
                System.out.println("Robô Selecionado: " + roboSelecionado.retornarNome() + 
                                   " (X:" + roboSelecionado.getX() + " Y:" + roboSelecionado.getY() + " Z:" + roboSelecionado.getZ() + 
                                   " Estado:" + roboSelecionado.getEstadoRobo() + ")");
                ambiente.visualizarAmbienteComDestaque(roboSelecionado);
            } else {
                System.out.println("Nenhum robô selecionado.");
                ambiente.visualizarAmbiente(); // Mostra Z=0 se nenhum robô selecionado
            }

            System.out.println("1. Listar todos os robôs (por tipo e estado)");
            System.out.println("2. Selecionar robô");
            System.out.println("3. Mover robô selecionado");
            System.out.println("4. Ligar/Desligar robô selecionado");
            System.out.println("5. Acionar sensores do robô selecionado (se Sensoreavel)");
            System.out.println("6. Executar tarefa específica do robô selecionado");
            System.out.println("7. Comunicar (robô selecionado envia mensagem, se Comunicavel)");
            System.out.println("8. Listar mensagens da Central de Comunicação");
            System.out.println("9. Visualizar mapa em uma camada Z específica");
            System.out.println("10. Acionar todos os sensores (comando do ambiente)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine(); // Limpar buffer
                continue;
            }

            try {
                switch (opcao) {
                    case 1:
                        System.out.println("Robôs no ambiente:");
                        if (ambiente.getRobos().isEmpty()) {
                            System.out.println("Nenhum robô cadastrado.");
                        } else {
                            for (Robo r : ambiente.getRobos()) {
                                String tipo = r.getClass().getSimpleName();
                                String sensoresInfo = "";
                                if (r instanceof Sensoreavel && !((Sensoreavel)r).getSensores().isEmpty()) {
                                    for(Sensor s : ((Sensoreavel)r).getSensores()){
                                        sensoresInfo += s.getTipoSensor() + "(R:"+s.getRaio()+"), ";
                                    }
                                    sensoresInfo = "Sensores: " + sensoresInfo.substring(0, sensoresInfo.length()-2);
                                } else if (!r.getSensores().isEmpty()){ // Sensores mesmo não sendo Sensoreavel via interface
                                     for(Sensor s : r.getSensores()){
                                        sensoresInfo += s.getTipoSensor() + "(R:"+s.getRaio()+"), ";
                                    }
                                    sensoresInfo = "Sensores (base): " + sensoresInfo.substring(0, sensoresInfo.length()-2);
                                }
                                 else {
                                    sensoresInfo = "Sem sensores listados";
                                }
                                System.out.println("- " + r.retornarNome() + " (" + tipo + ") Estado: " + r.getEstadoRobo() + 
                                                   " Pos:(" + r.getX()+","+r.getY()+","+r.getZ()+") " + sensoresInfo);
                            }
                        }
                        break;
                    case 2:
                        System.out.print("Digite o nome do robô para controlar: ");
                        nomeControle = scanner.nextLine();
                        Robo tempRobo = ambiente.getRoboPorNome(nomeControle);
                        if (tempRobo == null) {
                            System.out.println("Robô não encontrado.");
                        } else {
                            roboSelecionado = tempRobo;
                            System.out.println(roboSelecionado.retornarNome() + " selecionado.");
                        }
                        break;
                    case 3: // Mover robô selecionado
                        if (roboSelecionado == null) {
                            System.out.println("Nenhum robô selecionado.");
                            break;
                        }
                        System.out.println("Mover " + roboSelecionado.retornarNome() + ". Posição atual: (X:" + roboSelecionado.getX() + " Y:" + roboSelecionado.getY() + " Z:" + roboSelecionado.getZ() + ")");
                        System.out.println("Direções: F (Frente), T (Trás), E (Esquerda), D (Direita), C (Cima), B (Baixo)");
                        System.out.print("Digite o comando de movimento (ex: F 2 para frente 2 unidades): ");
                        String[] movimentoCmd = scanner.nextLine().toUpperCase().split(" ");
                        if (movimentoCmd.length < 1) { System.out.println("Comando inválido."); break;}
                        char dir = movimentoCmd[0].charAt(0);
                        int unidades = 1; // Mover 1 unidade por padrão se não especificado
                        if(movimentoCmd.length > 1) {
                            try { unidades = Integer.parseInt(movimentoCmd[1]); }
                            catch (NumberFormatException e){ System.out.println("Número de unidades inválido."); break;}
                        }

                        int atualX = roboSelecionado.getX();
                        int atualY = roboSelecionado.getY();
                        int atualZ = roboSelecionado.getZ();
                        int novoX = atualX, novoY = atualY, novoZ = atualZ;

                        switch (dir) {
                            case 'F': novoY += unidades; break; 
                            case 'T': novoY -= unidades; break;
                            case 'E': novoX -= unidades; break;
                            case 'D': novoX += unidades; break;
                            case 'C': novoZ += unidades; break;
                            case 'B': novoZ -= unidades; break;
                            default: System.out.println("Direção inválida."); continue;
                        }
                        ambiente.moverEntidade(roboSelecionado, novoX, novoY, novoZ);
                        System.out.println(roboSelecionado.retornarNome() + " nova posição: (X:" + roboSelecionado.getX() + " Y:" + roboSelecionado.getY() + " Z:" + roboSelecionado.getZ() + ")");
                        break;
                    case 4: // Ligar/Desligar
                        if (roboSelecionado == null) { System.out.println("Nenhum robô selecionado."); break; }
                        if (roboSelecionado.estaLigado()) {
                            roboSelecionado.desligar();
                        } else {
                            roboSelecionado.ligar();
                        }
                        System.out.println(roboSelecionado.retornarNome() + " agora está " + roboSelecionado.getEstadoRobo());
                        break;
                    case 5: // Acionar Sensores do Robô Selecionado
                         if (roboSelecionado == null) { System.out.println("Nenhum robô selecionado."); break; }
                         if (roboSelecionado instanceof Sensoreavel) {
                             ((Sensoreavel) roboSelecionado).acionarSensores(ambiente);
                         } else {
                             // Verifica se tem o método da classe base Robo, mesmo não sendo "Sensoreavel" formalmente
                             try { roboSelecionado.acionarSensor(ambiente); }
                             catch(RoboDesligadoException e){ System.out.println(e.getMessage());}
                             System.out.println(roboSelecionado.retornarNome() + " não implementa Sensoreavel (sensores base acionados se houver).");
                         }
                        break;
                    case 6: // Executar Tarefa Específica
                        if (roboSelecionado == null) { System.out.println("Nenhum robô selecionado."); break; }
                        roboSelecionado.executarTarefa(ambiente);
                        break;
                    case 7: // Comunicar
                        if (roboSelecionado == null) { System.out.println("Nenhum robô selecionado."); break; }
                        if (!(roboSelecionado instanceof Comunicavel)) {
                            System.out.println(roboSelecionado.retornarNome() + " não pode comunicar.");
                            break;
                        }
                        System.out.print("Digite o nome do robô destinatário: ");
                        String nomeDest = scanner.nextLine();
                        Robo roboDest = ambiente.getRoboPorNome(nomeDest);
                        if (roboDest == null) { System.out.println("Robô destinatário não encontrado."); break; }
                        if (!(roboDest instanceof Comunicavel)) { System.out.println(roboDest.retornarNome() + " não é comunicável."); break; }
                        
                        System.out.print("Digite a mensagem: ");
                        String msg = scanner.nextLine();
                        ((Comunicavel) roboSelecionado).enviarMensagem((Comunicavel) roboDest, msg, centralComunicacao);
                        break;
                    case 8: // Listar Mensagens da Central
                        centralComunicacao.exibirMensagens();
                        break;
                    case 9: // Visualizar mapa em Z específico
                        System.out.print("Digite a camada Z para visualizar (0 a "+(ambiente.getProfundidade()-1)+"): ");
                        int zLayer = scanner.nextInt(); scanner.nextLine(); // consome newline
                        ambiente.visualizarAmbienteNoPlanoZ(zLayer);
                        break;
                    case 10: // Acionar todos os sensores via ambiente
                        ambiente.executarTodosOsSensores();
                        break;
                    case 0:
                        System.out.println("Encerrando simulador...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (RoboDesligadoException | ColisaoException | ForaDosLimitesException | AcaoNaoPermitidaException | ErroComunicacaoException | EnergiaCuraException | SobreCargaExplosivaException e) {
                System.err.println("ERRO DURANTE AÇÃO: " + e.getMessage());
            } catch (Exception e){ 
                System.err.println("ERRO INESPERADO NO SISTEMA: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}