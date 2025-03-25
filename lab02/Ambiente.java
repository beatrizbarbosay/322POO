import java.util.ArrayList; //importa a biblioteca para criar listas
import java.util.HashMap;

public class Ambiente {
    private int largura;
    private int altura;
    private int profundidade; //para robos aereos
    private ArrayList<Robo> robosnoespaco; //lista de robos
    private HashMap<String, String> minerais; // Armazena minerais nas coordenadas



    // Construtor
    public Ambiente(int x, int y, int z) {
        largura = x;
        altura = y;
        profundidade = z; //para robos aereos
        robosnoespaco = new ArrayList<Robo>(); //cria a lista vazia para armazenar os robos
        minerais = new HashMap<>();
    }


    // Método para verificar se uma posição está dentro dos limites do ambiente
    public boolean dentroDosLimites(int x, int y, int z) {
        return x >= 0 && x <= largura && y >= 0 && y <= altura && z >= 0 && z <= profundidade; //adiciona validações para garantir que os robos n ao se movam para coordenadas negativas
    }

    public void adicionarRobo(Robo r) {
        robosnoespaco.add(r); //adiciona um robo a lista
    }

    public void adicionarMineral(int x, int y, String tipoMineral) {
        String chave = x + "," + y;
        minerais.put(chave, tipoMineral);
    }

    public boolean existeMineral(int x, int y, String tipoMineral) {
        String chave = x + "," + y;
        return minerais.containsKey(chave) && minerais.get(chave).equals(tipoMineral);
    }

    public void removerMineral(int x, int y) {
        String chave = x + "," + y;
        minerais.remove(chave);
    }

}

