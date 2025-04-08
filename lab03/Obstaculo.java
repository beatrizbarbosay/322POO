public class Obstaculo {
    private int x;
    private int y; //nao vou colocar altura aqui porque a altura ja esta no enum
    private int x2;
    private int y2;
    private TipoObstaculo tipo;

    public Obstaculo(int x, int y, int x2, int y2, TipoObstaculo tipo) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.tipo = tipo;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getX2() {
        return x2;
    }
    public int getY2() {
        return y2;
    }
    public TipoObstaculo getTipo() {
        return tipo;
    }

}
