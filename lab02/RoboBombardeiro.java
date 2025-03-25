class RoboBombardeiro extends RoboTerrestre {
    private int quantidadeBombas;

    public RoboBombardeiro(String n, int x, int y, String d, int v, int b) {
        super(n, x, y, d, v);
        quantidadeBombas = b;
    }

    public void deixarBomba() {
        if (quantidadeBombas > 0) {
            System.out.println("Robô " + retornarNome() + " deixou uma bomba na posição (" + retornarX() + ", " + retornarY() + ").");
            quantidadeBombas--;
        } else {
            System.out.println("Robô " + retornarNome() + " está sem bombas");
        }
    }
}