package robo;

import java.util.ArrayList;
import ambiente.*;
import comunicacao.*;
import exceptions.*;
import interfaces.*;
import missao.Missao;
import robo.subsistemas.ControleMovimento;
import robo.subsistemas.GerenciadorSensores;
import robo.subsistemas.ModuloComunicacao;
import sensores.Sensor;
import sensores.Sensoreavel;

// A classe Robo agora é o centro que coordena todos os seus subsistemas e implementa as interfaces principais.
public abstract class Robo implements Entidade, Sensoreavel, Comunicavel {
    
    // Atributos principais do Robô
    private String nome;
    protected int posicaoX;
    protected int posicaoY;
    protected int posicaoZ;
    private String direcao;
    private EstadoRobo estado;
    protected final TipoEntidade tipoEntidadeAtributo = TipoEntidade.ROBO;
    protected ControleMovimento controleMovimento;
    protected GerenciadorSensores gerenciadorSensores;
    protected ModuloComunicacao moduloComunicacao;

    public Robo(String n, int x, int y, int z, String d) {
        this.nome = n;
        this.posicaoX = x;
        this.posicaoY = y;
        this.posicaoZ = z;
        this.direcao = d;
        this.estado = EstadoRobo.LIGADO;
        
        this.controleMovimento = new ControleMovimento(this);
        this.gerenciadorSensores = new GerenciadorSensores(this);
        this.moduloComunicacao = new ModuloComunicacao(this);
    }
    
    public abstract void executarTarefa(Ambiente ambiente) throws RoboDesligadoException, AcaoNaoPermitidaException, EnergiaCuraException, SobreCargaExplosivaException;

    public void moverPara(int novoX, int novoY, int novoZ, Ambiente ambiente)
            throws RoboDesligadoException, AcaoNaoPermitidaException, ForaDosLimitesException, ColisaoException {
        this.controleMovimento.moverPara(novoX, novoY, novoZ, ambiente);
        
        // 2. Se for válido, o Robô (dono dos dados) atualiza sua posição
        this.posicaoX = novoX;
        this.posicaoY = novoY;
        this.posicaoZ = novoZ;
    }

    @Override
    public void acionarSensores(Ambiente ambiente) throws RoboDesligadoException {
        this.gerenciadorSensores.acionarSensores(ambiente);
    }
    
    public void adicionarSensor(Sensor sensor) {
        this.gerenciadorSensores.adicionarSensor(sensor);
    }

    @Override
    public ArrayList<Sensor> getSensores() {
        return this.gerenciadorSensores.getSensores();
    }
    
    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem, CentralComunicacao central) 
            throws RoboDesligadoException, ErroComunicacaoException {
        this.moduloComunicacao.enviarMensagem(destinatario, mensagem, central);
    }

    @Override
    public void receberMensagem(String mensagem, String idRemetente) throws RoboDesligadoException {
        this.moduloComunicacao.receberMensagem(mensagem, idRemetente);
    }

    
    public String retornarNome() { return nome; }
    public String getDirecao() { return direcao; }
    public void setDirecao(String novaDirecao){ this.direcao = novaDirecao; }
    public void ligar() { this.estado = EstadoRobo.LIGADO; System.out.println("Robô " + nome + " foi ligado."); }
    public void desligar() { this.estado = EstadoRobo.DESLIGADO; System.out.println("Robô " + nome + " foi desligado."); }
    public boolean estaLigado() { return this.estado == EstadoRobo.LIGADO; }
    public EstadoRobo getEstadoRobo() { return estado; }

    @Override
    public int getX() { return posicaoX; }
    @Override
    public int getY() { return posicaoY; }
    @Override
    public int getZ() { return posicaoZ; }
    @Override
    public TipoEntidade getTipo() { return tipoEntidadeAtributo; }
    @Override
    public String getDescricao() { return "Robô " + nome + " (Tipo: " + this.getClass().getSimpleName() + ")"; }
    @Override
    public char getRepresentacao() {
        if (this instanceof RoboBombardeiro) return 'B';
        if (this instanceof RoboMinerador) return 'M';
        if (this instanceof RoboAereoFada) return 'F';
        if (this instanceof RoboAereoFantasma) return 'G';
        if (this instanceof RoboExplorador) return 'E';
        if (this instanceof RoboAereo) return 'A';
        if (this instanceof RoboTerrestre) return 'T';
        return 'R';
    }
}