package robo;

import missao.Missao;
import ambiente.Ambiente;


public abstract class AgenteInteligente extends Robo {
    
    protected Missao missao;

    public AgenteInteligente(String n, int x, int y, int z, String d) {
        super(n, x, y, z, d);
    }
    public void definirMissao(Missao m) {
        this.missao = m;
    }
    public boolean temMissao() {
        return this.missao != null;
    }
    public abstract void executarMissao(Ambiente a);
}
