import java.util.ArrayList; //importa a biblioteca para criar listas
import java.util.HashMap;
import java.util.List;

// Classe que representa o ambiente onde os robôs se movimentam
public class Ambiente {
    private int largura; // Largura do ambiente
    private int altura; // Altura do ambiente
    private int profundidade; // Profundidade do ambiente (altura para robôs aéreos)
    private HashMap<String, String> minerais; // Armazena os minerais nas coordenadas

    private ArrayList<Entidade> entidades;
    private TipoEntidade[][][] mapa;
    private List<int[]> colisoes = new ArrayList<>();

    // Construtor que define o tamanho do ambiente
    public Ambiente(int x, int y, int z) {
        largura = x;
        altura = y;
        profundidade = z;
        minerais = new HashMap<>();
        
        entidades = new ArrayList<>();
        mapa = new TipoEntidade[largura][altura][profundidade];
        inicializarMapa();
    }

    // Inicializa todas as posições como VAZIO
    public void inicializarMapa() {
        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                for (int z = 0; z < profundidade; z++) {
                    mapa[x][y][z] = TipoEntidade.VAZIO;
                }
            }
        }
    }

    public void registrarColisao(int x, int y, int z) {
        colisoes.add(new int[]{x, y, z});
    }

    // Adiciona uma entidade ao ambiente
    public void adicionarEntidade(Entidade e) {
        try {
            dentroDosLimites(e.getX(), e.getY(), e.getZ());
            if (!estaOcupado(e.getX(), e.getY(), e.getZ())) {
                entidades.add(e);
                mapa[e.getX()][e.getY()][e.getZ()] = e.getTipo();
            } else {
                System.out.println("Erro ao adicionar entidade: posição ocupada.");
            }
        } catch (ForaDosLimitesException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    // Remove entidade do ambiente
    public void removerEntidade(Entidade e) {
        entidades.remove(e);
        mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;
    }

    // Método para verificar se uma posição está dentro dos limites do ambiente
    public void dentroDosLimites(int x, int y, int z) throws ForaDosLimitesException {
        if (x < 0 || x >= largura || y < 0 || y >= altura || z < 0 || z >= profundidade) {
            throw new ForaDosLimitesException("Posição (" + x + ", " + y + ", " + z + ") está fora dos limites do ambiente.");
        }
    }

    // Verifica se posição está ocupada
    public boolean estaOcupado(int x, int y, int z) {
        return mapa[x][y][z] != TipoEntidade.VAZIO;
    }

    // Move uma entidade para uma nova posição
    public void moverEntidade(Entidade e, int novoX, int novoY, int novoZ) {
        try {
            dentroDosLimites(novoX, novoY, novoZ);
            if (estaOcupado(novoX, novoY, novoZ)) {
                System.out.println("Movimento bloqueado: destino ocupado.");
                return;
            }

            mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;

            if (e instanceof Robo) {
                ((Robo) e).mover(novoX - e.getX(), novoY - e.getY(), this);  // lança as exceções
                if (e instanceof RoboAereo) {
                    ((RoboAereo) e).descer(e.getZ());
                    ((RoboAereo) e).subir(novoZ);
                }
            }

            mapa[novoX][novoY][novoZ] = e.getTipo();

        } catch (ForaDosLimitesException | ColisaoException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public void verificarColisoes() throws ColisaoException {
        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                for (int z = 0; z < profundidade; z++) {
                    int contador = 0;
                    for (Entidade e : entidades) {
                        if (e.getX() == x && e.getY() == y && e.getZ() == z) {
                            contador++;
                        }
                    }
                    if (contador > 1) {
                        throw new ColisaoException("Colisão detectada na posição (" + x + "," + y + "," + z + ")");
                    }
                }
            }
        }
    }


    // Retorna todas as entidades
    public ArrayList<Entidade> getEntidades() {
        return entidades;
    }

    // Visualiza o plano XY no nível Z = 0
    public void visualizarAmbiente() {
        System.out.println("Mapa do ambiente (Z = 0):");
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                char c = '.';
                boolean houveColisao = false;

                for (int[] pos : colisoes) {
                    if (pos[0] == x && pos[1] == y && pos[2] == 0) {
                        c = '*';
                        houveColisao = true;
                        break;
                    }
                }

                if (!houveColisao) {
                    for (Entidade e : entidades) {
                        if (e.getX() == x && e.getY() == y && e.getZ() == 0) {
                            c = e.getRepresentacao();
                            break;
                        }
                    }
                }

                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public void visualizarAmbienteComDestaque(Robo roboSelecionado) {
        System.out.println("Mapa do ambiente (Z = 0):");
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                boolean ehColisao = false;
                for (int[] c : colisoes) {
                    if (c[0] == x && c[1] == y && c[2] == 0) {
                        ehColisao = true;
                        break;
                    }
                }

                char c = '.';
                for (Entidade e : entidades) {
                    if (e.getX() == x && e.getY() == y && e.getZ() == 0) {
                        c = e.getRepresentacao();
                        break;
                    }
                }

                if (ehColisao) {
                    System.out.print("[*] ");
                } else if (roboSelecionado.getX() == x && roboSelecionado.getY() == y && roboSelecionado.getZ() == 0) {
                    System.out.print("[" + c + "] ");
                } else {
                    System.out.print(" " + c + "  ");
                }
            }
            System.out.println();
        }
    }

    // Método para adicionar um mineral ao ambiente
    public void adicionarMineral(int x, int y, String tipoMineral) {
        String chave = x + "," + y;
        minerais.put(chave, tipoMineral);
    }

    // Verifica se há o mineral na coordenada
    public boolean existeMineral(int x, int y, String tipoMineral) {
        String chave = x + "," + y;
        return minerais.containsKey(chave) && minerais.get(chave).equals(tipoMineral);
    }

    // Método para remover um mineral ao ambiente
    public void removerMineral(int x, int y) {
        String chave = x + "," + y;
        minerais.remove(chave);
    }

    public ArrayList<Obstaculo> getObstaculos() {
    ArrayList<Obstaculo> obstaculos = new ArrayList<>();
    for (Entidade e : entidades) {
        if (e instanceof Obstaculo) {
            obstaculos.add((Obstaculo) e);
        }
    }
    return obstaculos;
}
}