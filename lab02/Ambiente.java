import java.util.ArrayList; //importa a biblioteca para criar listas
import java.util.HashMap;

// Classe que representa o ambiente onde os robôs se movimentam
public class Ambiente {
    private int largura; // Largura do ambiente
    private int altura; // Altura do ambiente
    private int profundidade; // Profundidade do ambiente (altura para robôs aéreos)
    private ArrayList<Robo> robosnoespaco; // Lista de robôs ativos no ambiente
    private HashMap<String, String> minerais; // Armazena os minerais nas coordenadas

    // Construtor que define o tamanho do ambiente
    public Ambiente(int x, int y, int z) {
        largura = x;
        altura = y;
        profundidade = z;
        robosnoespaco = new ArrayList<Robo>(); // Inicializa a lista de robôs ativos
        minerais = new HashMap<>();
    }


    // Método para verificar se uma posição está dentro dos limites do ambiente
    public boolean dentroDosLimites(int x, int y, int z) {
        return x >= 0 && x <= largura && y >= 0 && y <= altura && z >= 0 && z <= profundidade; // Adiciona validações para garantir que os robôs não se movam para coordenadas negativas
    }

    // Método para adicionar um robô ao ambiente
    public void adicionarRobo(Robo r) {
        robosnoespaco.add(r); //adiciona um robo a lista
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