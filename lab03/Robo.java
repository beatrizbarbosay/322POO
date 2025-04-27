// Classe base para todos os robôs

import java.util.ArrayList;

public class Robo {
    private String nome; // Nome do robô
    private int posicaoX; // Posição X do robô no ambiente
    private int posicaoY; // Posição Y do robô no ambiente
    private String direcao; // Direção atual do robô (Norte, Sul, Leste, Oeste)
    protected ArrayList<Sensor> sensores;
    // Construtor da classe Robo
    public Robo(String n, int x, int y, String d) {
        nome = n;  // Inicializa o nome do robô
        posicaoX = x;  // Inicializa a posição X
        posicaoY = y;  // Inicializa a posição Y
        direcao = d;  // Inicializa a direção
        sensores = new ArrayList<>(); // Inicializa a lista de sensores
    }

    // Método para mover o robô para uma nova posição
    public void mover(int deltaX, int deltaY) {
        posicaoX += deltaX; // Atualiza a posição X
        posicaoY += deltaY; // Atualiza a posição Y
    }
    
    public ArrayList<Sensor> getSensores() {
        return sensores;
    }

    // Método para adicionar um sensor ao robô
    public void adicionarSensor(Sensor sensor) {
        sensores.add(sensor); // Adiciona o sensor à lista de sensores
    }

    public void acionarSensor(Ambiente ambiente) {
        for (Sensor sensor : sensores) {
            sensor.monitorar(this, ambiente); // Aciona cada sensor no ambiente
        }
    }

    // Métodos para acessar as propriedades do robô
    public int retornarX() {
        return posicaoX;
    }
    public int retornarY() {
        return posicaoY;
    }
    public String retornarNome() {
        return nome;
    }
    
    
    // Método para exibir a posição do robô no ambiente
    public void exibirPosicao() {
        System.out.println("Robô " + nome + " está na posição (" + posicaoX + ", " + posicaoY + ")");
    }

    //identifica outros robos como obstáculos, exceto se for um robô fantasma
    public void identificarObstaculo(Robo outroRobo) {
        if (outroRobo instanceof RoboAereoFantasma) {
            System.out.println("Robô " + retornarNome() + " não detecta obstáculos fantasmas.");
        } else {
            System.out.println("Robô " + retornarNome() + " detectou um obstáculo na posição (" + outroRobo.retornarX() + ", " + outroRobo.retornarY() + ")");
        }
    }
    
}