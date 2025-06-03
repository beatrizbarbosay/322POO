public class RoboAereo extends Robo {
    private int altitudemax; 

    public RoboAereo(String n, int x, int y, String d, int a, int am) { // a: altitude inicial (Z), am: altitude máxima
        super(n, x, y, a, d);
        this.altitudemax = am;
        if (this.posicaoZ > this.altitudemax) { // Garante que Z inicial não exceda o máximo
            System.out.println("Aviso: Altitude inicial ("+this.posicaoZ+") do RoboAereo "+retornarNome()+" excedia o máximo ("+am+"). Ajustado para o máximo.");
            this.posicaoZ = this.altitudemax;
        }
        if (this.posicaoZ < 0) { // Garante que Z inicial não seja negativo
             System.out.println("Aviso: Altitude inicial ("+this.posicaoZ+") do RoboAereo "+retornarNome()+" era negativa. Ajustado para 0.");
            this.posicaoZ = 0;
        }
    }

    @Override
    public void moverPara(int novoX, int novoY, int novoZ, Ambiente ambiente)
            throws ColisaoException, ForaDosLimitesException, RoboDesligadoException, AcaoNaoPermitidaException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " está desligado. Não pode mover.");
        }

        if (novoZ < 0) {
            throw new AcaoNaoPermitidaException("Robô aéreo " + retornarNome() + " não pode ter altitude negativa. Tentativa: " + novoZ);
        }
        if (novoZ > this.altitudemax) {
            throw new AcaoNaoPermitidaException("Robô aéreo " + retornarNome() + " não pode exceder altitude máxima de " 
                                                + this.altitudemax + ". Tentativa: " + novoZ);
        }

        this.posicaoX = novoX;
        this.posicaoY = novoY;
        this.posicaoZ = novoZ; // altitudeAtual é posicaoZ
        System.out.println(retornarNome() + " (Aéreo) moveu para (" + this.posicaoX + ", " + this.posicaoY + ", " + this.posicaoZ + ")");
    }

    public void subir(int metros, Ambiente ambiente) throws RoboDesligadoException, ForaDosLimitesException, ColisaoException, AcaoNaoPermitidaException {
        if (!estaLigado()) throw new RoboDesligadoException(retornarNome() + " desligado.");
        int novaAltitude = this.posicaoZ + metros;
        ambiente.moverEntidade(this, this.posicaoX, this.posicaoY, novaAltitude);
    }

    public void descer(int metros, Ambiente ambiente) throws RoboDesligadoException, ForaDosLimitesException, ColisaoException, AcaoNaoPermitidaException {
        if (!estaLigado()) throw new RoboDesligadoException(retornarNome() + " desligado.");
        int novaAltitude = this.posicaoZ - metros;
        ambiente.moverEntidade(this, this.posicaoX, this.posicaoY, novaAltitude);
    }

    @Override
    public void executarTarefa(Ambiente ambiente) throws RoboDesligadoException, AcaoNaoPermitidaException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " está desligado.");
        }
        System.out.println("Robô Aéreo " + retornarNome() + " executando sua tarefa: Reconhecimento aéreo na altitude " + posicaoZ + ".");
        int dx = (Math.random() > 0.66) ? 1 : (Math.random() > 0.33 ? -1 : 0);
        int dy = (Math.random() > 0.66) ? 1 : (Math.random() > 0.33 ? -1 : 0);
        if (dx != 0 || dy != 0) {
            try {
                ambiente.moverEntidade(this, posicaoX + dx, posicaoY + dy, posicaoZ);
            } catch (Exception e) {
                System.out.println(retornarNome() + " não pôde fazer reconhecimento em ("+dx+","+dy+"): " + e.getMessage());
            }
        }
    }

    public int getAltitude() { 
        return this.posicaoZ;
    }

    public int getAltitudeMax() { 
        return this.altitudemax;
    }

    @Override
    public void exibirPosicao() {
        System.out.println("Robô " + retornarNome() + " está na posição (" + retornarX() + ", " + retornarY() + ", " + getAltitude() + "), Estado: " + getEstadoRobo());
    }
}