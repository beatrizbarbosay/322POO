// Classe que herda de RoboTerrestre e representa um robô minerador
class RoboMinerador extends RoboTerrestre {
    private String tipoMineral; // Tipo de mineral que o robô minerador está buscando
    private Ambiente ambiente; // Ambiente onde o robô minerador está

    // Construtor que recebe o nome, posição, direção, velocidade, tipo de mineral e o ambiente
    public RoboMinerador(String n, int x, int y, String d, int v, String m, Ambiente ambiente) {
        super(n, x, y, d, v); // Chama o construtor da classe RoboTerrestre
        tipoMineral = m; // Define o tipo de mineral que o robô minerador procura
        this.ambiente = ambiente; // Define o ambiente no qual o robô minerador opera
    }

    // Método para minerar o mineral no ambiente
    public void minerar() {
        // O robô só consegue minerar se o mineral que ele é capaz de minerar estiver em sua mesma coordenada
        if (ambiente.existeMineral(retornarX(), retornarY(), tipoMineral)) {
            System.out.println("Robo " + retornarNome() + " minerou " + tipoMineral + " na posição (" + retornarX() + ", " + retornarY() + ")");
            ambiente.removerMineral(retornarX(), retornarY()); // Remove o mineral do ambiente
        } else {
            System.out.println("Robo " + retornarNome() + " não encontrou " + tipoMineral + " para minerar na posição (" + retornarX() + ", " + retornarY() + ")");
        }
    }
}