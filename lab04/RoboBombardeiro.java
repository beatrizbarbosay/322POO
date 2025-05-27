// Classe que herda de RoboTerrestre e representa um robô bombardeiro

class RoboBombardeiro extends RoboTerrestre implements Sensoreavel, Atacante {
    private int quantidadeBombas; // Quantidade de bombas que o robô possui
    private int bombasAtivas;
    private static final int LIMITE_MAXIMO = 5; // Limite máximo de bombas ativas

    // Construtor que recebe o nome, posição, direção, velocidade e quantidade de bombas
    public RoboBombardeiro(String n, int x, int y, String d, int v, int b) {
        super(n, x, y, d, v); // Chama o construtor da classe RoboTerrestre
        quantidadeBombas = b; // Define a quantidade de bombas do robô
        bombasAtivas = 0; // Inicializa o contador de bombas ativas
    }

    // Método para o robô deixar uma bomba
    public void deixarBomba() {
        //O robô só deixa uma bomba na posição para qual ele se moveu se ele tiver pelo menos uma bomba
        if (quantidadeBombas > 0) {
            System.out.println("Robô " + retornarNome() + " deixou uma bomba na posição (" + retornarX() + ", " + retornarY() + ").");
            quantidadeBombas--; // Diminui em um a quantidade de bombas do robô
        } else {
            System.out.println("Robô " + retornarNome() + " está sem bombas");
        }
    }
    @Override
    public void atacar(int x, int y, Ambiente ambiente) throws SobreCargaExplosivaException{
        if (bombasAtivas > LIMITE_MAXIMO) {
            throw new SobreCargaExplosivaException("O robô bombardeiro " + retornarNome() + " tentou lançar bombas demais e sobrecarregou!");
        }
        System.out.println("Robô " + retornarNome() + " lançou um ataque na posição (" + x + ", " + y + ")!");
        bombasAtivas++;

        for (Entidade entidade : ambiente.getEntidades()) {
            if (entidade instanceof Robo roboAlvo) {
                if (roboAlvo != this && roboAlvo.retornarX() == x && roboAlvo.retornarY() == y && roboAlvo.getZ() == this.getZ()) {
                    System.out.println("Robô " + retornarNome() + " atingiu o robô " + roboAlvo.retornarNome() + "!");
                    // Aqui você poderia implementar uma lógica para 'danificar' ou 'remover' o robô, se necessário
                }
            }
        }
    }
            
}