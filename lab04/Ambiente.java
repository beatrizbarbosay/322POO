import java.util.ArrayList; //importa a biblioteca para criar listas
import java.util.HashMap;

// Classe que representa o ambiente onde os robôs se movimentam
public class Ambiente {
    private int largura; // Largura do ambiente
    private int altura; // Altura do ambiente
    private int profundidade; // Profundidade do ambiente (altura para robôs aéreos)
    private HashMap<String, String> minerais; // Armazena os minerais nas coordenadas

    private ArrayList<Entidade> entidades;
    private TipoEntidade[][][] mapa;


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

    // Adiciona uma entidade ao ambiente
    public void adicionarEntidade(Entidade e) {
        if (dentroDosLimites(e.getX(), e.getY(), e.getZ()) && !estaOcupado(e.getX(), e.getY(), e.getZ())) {
            entidades.add(e);
            mapa[e.getX()][e.getY()][e.getZ()] = e.getTipo();
        } else {
            System.out.println("Erro ao adicionar entidade: posição ocupada ou fora dos limites.");
        }
    }

    // Remove entidade do ambiente
    public void removerEntidade(Entidade e) {
        entidades.remove(e);
        mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;
    }

    // Método para verificar se uma posição está dentro dos limites do ambiente
    public boolean dentroDosLimites(int x, int y, int z) {
        return x >= 0 && x <= largura && y >= 0 && y <= altura && z >= 0 && z <= profundidade; // Adiciona validações para garantir que os robôs não se movam para coordenadas negativas
    }

    // Verifica se posição está ocupada
    public boolean estaOcupado(int x, int y, int z) {
        return mapa[x][y][z] != TipoEntidade.VAZIO;
    }

    // Move uma entidade para uma nova posição
    public void moverEntidade(Entidade e, int novoX, int novoY, int novoZ) {
        if (!dentroDosLimites(novoX, novoY, novoZ)) {
            System.out.println("Movimento inválido: fora dos limites.");
            return;
        }
        if (estaOcupado(novoX, novoY, novoZ)) {
            System.out.println("Movimento bloqueado: destino ocupado.");
            return;
        }

        // Atualiza mapa
        mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;

        // Atualiza posição da entidade
        if (e instanceof Robo) {
            ((Robo) e).mover(novoX - e.getX(), novoY - e.getY()); // mover em X e Y
            if (e instanceof RoboAereo) {
                ((RoboAereo) e).descer(e.getZ()); // desce até 0 antes de atualizar
                ((RoboAereo) e).subir(novoZ);     // sobe até novoZ
            }
        }

        // Atualiza nova posição no mapa
        mapa[novoX][novoY][novoZ] = e.getTipo();
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
                for (Entidade e : entidades) {
                    if (e.getX() == x && e.getY() == y && e.getZ() == 0) {
                        c = e.getRepresentacao();
                        break;
                    }
                }
                System.out.print(c + " ");
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
}