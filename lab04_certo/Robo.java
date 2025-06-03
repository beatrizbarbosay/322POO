import java.util.ArrayList;

public abstract class Robo implements Entidade {
    private String nome; 
    protected int posicaoX;
    protected int posicaoY;
    protected int posicaoZ; 
    private String direcao;
    protected ArrayList<Sensor> sensores;
    private EstadoRobo estado; 
    protected final TipoEntidade tipoEntidadeAtributo = TipoEntidade.ROBO; 

    public Robo(String n, int x, int y, String d) { 
        this(n, x, y, 0, d);
    }

    public Robo(String n, int x, int y, int z, String d) {
        this.nome = n;
        this.posicaoX = x;
        this.posicaoY = y;
        this.posicaoZ = z;
        this.direcao = d;
        this.sensores = new ArrayList<>();
        this.estado = EstadoRobo.LIGADO; 
    }

    public abstract void moverPara(int novoX, int novoY, int novoZ, Ambiente ambiente)
            throws ColisaoException, ForaDosLimitesException, RoboDesligadoException, AcaoNaoPermitidaException;

    // AQUI A MUDANÇA:
    public abstract void executarTarefa(Ambiente ambiente)
            throws RoboDesligadoException, AcaoNaoPermitidaException, EnergiaCuraException, SobreCargaExplosivaException;


    public ArrayList<Sensor> getSensores() {
        return sensores;
    }

    public void adicionarSensor(Sensor sensor) {
        sensores.add(sensor);
    }

    public void acionarSensor(Ambiente ambiente) throws RoboDesligadoException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " está desligado e não pode acionar sensores.");
        }
        for (Sensor sensor : sensores) {
            sensor.monitorar(this, ambiente);
        }
    }

    public int retornarX() {
        return posicaoX;
    }

    public int retornarY() {
        return posicaoY;
    }

    public String retornarNome() {
        return nome;
    }
    
    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String novaDirecao){
        this.direcao = novaDirecao;
    }

    public void exibirPosicao() {
        System.out.println("Robô " + nome + " está na posição (" + posicaoX + ", " + posicaoY + ", " + posicaoZ + "), Estado: " + estado);
    }

    public void identificarObstaculo(Robo outroRobo) {
        if (outroRobo instanceof RoboAereoFantasma) {
            System.out.println("Robô " + retornarNome() + " não detecta obstáculos fantasmas.");
        } else {
            System.out.println("Robô " + retornarNome() + " detectou um obstáculo na posição (" + outroRobo.retornarX() + ", " + outroRobo.retornarY() + ")");
        }
    }

    public void ligar() {
        this.estado = EstadoRobo.LIGADO;
        System.out.println("Robô " + nome + " foi ligado.");
    }

    public void desligar() {
        this.estado = EstadoRobo.DESLIGADO;
        System.out.println("Robô " + nome + " foi desligado.");
    }

    public boolean estaLigado() {
        return this.estado == EstadoRobo.LIGADO;
    }
    
    public EstadoRobo getEstadoRobo() {
        return estado;
    }

    @Override
    public int getX() {
        return posicaoX;
    }

    @Override
    public int getY() {
        return posicaoY;
    }

    @Override
    public int getZ() {
        return posicaoZ;
    }

    @Override
    public TipoEntidade getTipo() {
        return tipoEntidadeAtributo; 
    }

    @Override
    public String getDescricao() {
        return "Robô " + nome + " (Tipo: " + this.getClass().getSimpleName() + ")";
    }

    @Override
    public char getRepresentacao() {
        if (this instanceof RoboBombardeiro) return 'B';
        if (this instanceof RoboMinerador) return 'M';
        if (this instanceof RoboAereoFada) return 'F';
        if (this instanceof RoboAereoFantasma) return 'G';
        if (this instanceof RoboAereo) return 'A';
        if (this instanceof RoboTerrestre) return 'T';
        return 'R';
    }

    public void verificarColisaoRobo(Robo outro) throws ColisaoException {
        if (this.retornarX() == outro.retornarX() && this.retornarY() == outro.retornarY() && this.getZ() == outro.getZ() && this != outro) {
            throw new ColisaoException("O robô " + this.retornarNome() + " colidiu com " + outro.retornarNome());
        }
    }
}