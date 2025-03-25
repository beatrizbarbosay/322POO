class RoboMinerador extends RoboTerrestre {
    private String tipoMineral;
    private Ambiente ambiente;

    public RoboMinerador(String n, int x, int y, String d, int v, String m, Ambiente ambiente) {
        super(n, x, y, d, v);
        tipoMineral = m;
        this.ambiente = ambiente;
    }

    public void minerar() {
        if (ambiente.existeMineral(retornarX(), retornarY(), tipoMineral)) {
            System.out.println("Robo " + retornarNome() + " minerou " + tipoMineral + " na posição (" + retornarX() + ", " + retornarY() + ")");
            ambiente.removerMineral(retornarX(), retornarY());
        } else {
            System.out.println("Robo " + retornarNome() + " não encontrou " + tipoMineral + " para minerar na posição (" + retornarX() + ", " + retornarY() + ")");
        }
    }
}