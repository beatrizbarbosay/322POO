package main;
import java.util.InputMismatchException;
import java.util.Scanner;
import ambiente.*;
import comunicacao.*;
import exceptions.*;
import interfaces.*;
import missao.*;
import robo.*;
import sensores.*;
import arquivos.Logger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ambiente ambiente = null;
        CentralComunicacao centralComunicacao = new CentralComunicacao();

        Logger.init("log_das_missoes.txt");

        try {
            ambiente = new Ambiente(20, 15, 5);
            Logger.log("Main: Ambiente criado com dimensões 20x15x5.");

            // Adicionando obstáculos
            ambiente.adicionarEntidade(new Obstaculo(2, 3, 0, TipoObstaculo.Fogo));
            ambiente.adicionarEntidade(new Obstaculo(8, 8, 0, TipoObstaculo.Cachoeira));
            Logger.log("Main: Obstáculos adicionados ao ambiente.");

            // Adicionando os robôs ao ambiente
            RoboTerrestre robo1 = new RoboTerrestre("Robson", 3, 2, "Norte", 5);
            ambiente.adicionarEntidade(robo1);

            RoboBombardeiro robo2 = new RoboBombardeiro("Roberta", 5, 5, "Sul", 3, 3);
            ambiente.adicionarEntidade(robo2);

            RoboMinerador robo3 = new RoboMinerador("Ronaldo", 10, 10, "Leste", 4, "Ferro");
            ambiente.adicionarEntidade(robo3);

            RoboAereo robo4 = new RoboAereo("Airton", 7, 7, "Oeste", 2, 4);
            ambiente.adicionarEntidade(robo4);
            
            RoboAereoFantasma roboFantasma = new RoboAereoFantasma("Gaspar", 1, 12, "Sul", 1, 3);
            ambiente.adicionarEntidade(roboFantasma);

            RoboAereoFada roboFada = new RoboAereoFada("Oyara", 17, 2, "Norte", 2, 4, "Rosa");
            ambiente.adicionarEntidade(roboFada);
            
            RoboExplorador roboExplorador = new RoboExplorador("Wall-E", 1, 8, "Sul", 2);
            ambiente.adicionarEntidade(roboExplorador);

            Missao missaoExplorar = new MissaoExplorar();
            roboExplorador.definirMissao(missaoExplorar);
            Logger.log("Main: Robôs adicionados. Missão de exploração definida para Wall-E.");

            System.out.println("CONTROLE DE ROBÔS INICIALIZADO");
            ambiente.visualizarAmbiente();

        } catch (Exception e) {
            Logger.log("FATAL: Erro Crítico na inicialização: " + e.getMessage());
            System.err.println("Erro Crítico na inicialização do ambiente ou entidades: " + e.getMessage());
            e.printStackTrace();
            Logger.close();
            return;
        }

        Robo roboSelecionado = null;

        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            if (roboSelecionado != null) {
                System.out.println("Robô Selecionado: " + roboSelecionado.retornarNome() +
                                   " (X:" + roboSelecionado.getX() + " Y:" + roboSelecionado.getY() + " Z:" + roboSelecionado.getZ() +
                                   " Estado:" + roboSelecionado.getEstadoRobo() + ")");
                ambiente.visualizarAmbienteComDestaque(roboSelecionado);
            } else {
                System.out.println("Nenhum robô selecionado.");
                ambiente.visualizarAmbiente();
            }

            System.out.println("1. Listar todos os robôs");
            System.out.println("2. Selecionar robô");
            System.out.println("3. Mover robô selecionado");
            System.out.println("4. Ligar/Desligar robô selecionado");
            System.out.println("5. Acionar sensores do robô selecionado");
            System.out.println("6. Executar tarefa específica do robô");
            System.out.println("7. Comunicar");
            System.out.println("8. Listar mensagens da Central");
            System.out.println("9. Visualizar mapa em uma camada Z");
            System.out.println("10. Acionar todos os sensores (ambiente)");
            System.out.println("11. Executar missão do robô selecionado");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
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
                                System.out.println("- " + r.getDescricao() + " | Estado: " + r.getEstadoRobo() +
                                                   " | Pos:(" + r.getX() + "," + r.getY() + "," + r.getZ() + ")");
                            }
                        }
                        break;
                    case 2:
                        System.out.print("Digite o nome do robô para controlar: ");
                        String nomeControle = scanner.nextLine();
                        Robo tempRobo = ambiente.getRoboPorNome(nomeControle);
                        if (tempRobo == null) {
                            System.out.println("Robô não encontrado.");
                        } else {
                            roboSelecionado = tempRobo;
                            System.out.println(roboSelecionado.retornarNome() + " selecionado.");
                        }
                        break;
                    case 3:
                        if (roboSelecionado == null) {
                            System.out.println("Nenhum robô selecionado.");
                            break;
                        }
                        System.out.print("Digite o movimento (dx dy dz), ex: 1 0 0 para mover 1 em X: ");
                        int dx = scanner.nextInt();
                        int dy = scanner.nextInt();
                        int dz = scanner.nextInt();
                        scanner.nextLine();
                        ambiente.moverEntidade(roboSelecionado, roboSelecionado.getX() + dx, roboSelecionado.getY() + dy, roboSelecionado.getZ() + dz);
                        break;
                    case 4:
                        if (roboSelecionado == null) { System.out.println("Nenhum robô selecionado."); break; }
                        if (roboSelecionado.estaLigado()) {
                            roboSelecionado.desligar();
                        } else {
                            roboSelecionado.ligar();
                        }
                        break;
                    case 5:
                         if (roboSelecionado == null) { System.out.println("Nenhum robô selecionado."); break; }
                         if (roboSelecionado instanceof Sensoreavel) {
                             ((Sensoreavel) roboSelecionado).acionarSensores(ambiente);
                         } else {
                             System.out.println(roboSelecionado.retornarNome() + " não é formalmente Sensoreavel.");
                         }
                        break;
                    case 6:
                        if (roboSelecionado == null) { System.out.println("Nenhum robô selecionado."); break; }
                        roboSelecionado.executarTarefa(ambiente);
                        break;
                    case 7:
                        if (roboSelecionado == null) { System.out.println("Nenhum robô selecionado."); break; }
                        if (!(roboSelecionado instanceof Comunicavel)) {
                            System.out.println(roboSelecionado.retornarNome() + " não pode comunicar.");
                            break;
                        }
                        System.out.print("Digite o nome do robô destinatário: ");
                        Robo roboDest = ambiente.getRoboPorNome(scanner.nextLine());
                        if (roboDest == null) { System.out.println("Robô destinatário não encontrado."); break; }
                        if (!(roboDest instanceof Comunicavel)) { System.out.println(roboDest.retornarNome() + " não é comunicável."); break; }
                        
                        System.out.print("Digite a mensagem: ");
                        String msg = scanner.nextLine();
                        ((Comunicavel) roboSelecionado).enviarMensagem((Comunicavel) roboDest, msg, centralComunicacao);
                        break;
                    case 8:
                        centralComunicacao.exibirMensagens();
                        break;
                    case 9:
                        System.out.print("Digite a camada Z para visualizar: ");
                        int zLayer = scanner.nextInt(); scanner.nextLine();
                        ambiente.visualizarAmbienteNoPlanoZ(zLayer);
                        break;
                    case 10:
                        ambiente.executarTodosOsSensores();
                        break;
                    case 11:
                        if (roboSelecionado == null) {
                            System.out.println("Nenhum robô selecionado.");
                            break;
                        }
                        if (roboSelecionado instanceof AgenteInteligente) {
                            ((AgenteInteligente) roboSelecionado).executarMissao(ambiente);
                        } else {
                            System.out.println("O robô " + roboSelecionado.retornarNome() + " não é um Agente Inteligente.");
                        }
                        break;
                    case 0:
                        System.out.println("Encerrando simulador...");
                        Logger.close();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                Logger.log("ERRO: " + e.getClass().getSimpleName() + " - " + e.getMessage());
                System.err.println("--- ERRO DURANTE AÇÃO: " + e.getClass().getSimpleName() + " - " + e.getMessage() + " ---");
            }
        }

    }
}