public class Obstaculo implements Entidade {
    private int x1;
    private int y1;
    private int z1;
    private TipoObstaculo tipo;

    public Obstaculo(int x1, int y1, int z1, TipoObstaculo tipo) {
        this.x1 = x1;
        this.y1 = y1;
        this.z1  = z1;
        this.tipo = tipo;
    }

    public int getX1() {
        return x1;
    }
    public int getY1() {
        return y1;
    }
    public int getZ1() {
        return z1;
    }
    public TipoObstaculo getTipoObstaculo() {
        return tipo;
    }

    // Métodos da interface Entidade

    @Override
    public int getX() {
        return x1;
    }

    @Override
    public int getY() {
        return y1;
    }

    @Override
    public int getZ() {
        return z1;
    }

    @Override
    public TipoEntidade getTipo() {
        return TipoEntidade.OBSTACULO;
    }

    @Override
    public String getDescricao() {
        return "Obstáculo do tipo " + tipo.name();
    }

    @Override
    public char getRepresentacao() {
        return 'O';
    }
}
