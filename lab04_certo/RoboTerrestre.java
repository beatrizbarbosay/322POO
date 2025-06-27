public class RoboTerrestre extends Robo {
    private int velocidadeMaxima;

    public RoboTerrestre(String n, int x, int y, String d, int v) {
        super(n, x, y, 0, d); // RoboTerrestre sempre no Z=0 inicialmente
        this.velocidadeMaxima = v;
    }

    @Override
    public void moverPara(int novoX, int novoY, int novoZ, Ambiente ambiente)
            throws ColisaoException, ForaDosLimitesException, RoboDesligadoException, AcaoNaoPermitidaException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " está desligado. Não pode mover.");
        }
        if (novoZ != this.posicaoZ) { 
             throw new AcaoNaoPermitidaException("Robô terrestre " + retornarNome() + " não pode se mover para Z=" + novoZ + " (fixo em Z="+this.posicaoZ+")");
        }
        

        int deltaX = novoX - this.posicaoX;
        int deltaY = novoY - this.posicaoY;

        if (Math.abs(deltaX) > velocidadeMaxima || Math.abs(deltaY) > velocidadeMaxima || (Math.abs(deltaX) + Math.abs(deltaY) == 0 && (novoX != this.posicaoX || novoY != this.posicaoY )) ) {
            if (Math.abs(deltaX) > velocidadeMaxima || Math.abs(deltaY) > velocidadeMaxima) {
                 throw new AcaoNaoPermitidaException("Movimento excede a velocidade máxima de " + velocidadeMaxima + " por eixo para o robô " + retornarNome());
            }
        }
        if(Math.abs(deltaX) == 0 && Math.abs(deltaY) == 0 && (novoX == this.posicaoX && novoY == this.posicaoY)) {
        }
        this.posicaoX = novoX;
        this.posicaoY = novoY;
        System.out.println(retornarNome() + " (Terrestre) moveu para (" + this.posicaoX + ", " + this.posicaoY + ", " + this.posicaoZ + ")");
    }

    @Override
    public void executarTarefa(Ambiente ambiente) throws RoboDesligadoException, AcaoNaoPermitidaException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " está desligado.");
        }
        System.out.println("Robô Terrestre " + retornarNome() + " executando sua tarefa: Patrulhando perímetro.");
        int dx = (Math.random() < 0.5) ? 1 : -1;
        if(Math.random() < 0.3) dx = 0;
        int dy = (Math.random() < 0.5) ? 1 : -1;
        if(Math.random() < 0.3) dy = 0;

        if (dx !=0 || dy != 0) {
            try {
                ambiente.moverEntidade(this, posicaoX + dx, posicaoY + dy, posicaoZ);
            } catch (Exception e) {
                System.out.println(retornarNome() + " não pôde patrulhar na direção ("+dx+","+dy+"): " + e.getMessage());
            }
        }
    }
    
    public int retornarVelocidadeMaxima() {
        return velocidadeMaxima;
    }
}