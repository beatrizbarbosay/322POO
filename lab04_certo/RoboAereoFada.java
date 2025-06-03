public class RoboAereoFada extends RoboAereo implements Sensoreavel, Curavel {
    private String corBrilho;
    private int energiaCura; 
    
    public RoboAereoFada(String n, int x, int y, String d, int a, int am, String c) {        
        super(n, x, y, d, a, am); // Passa altitude inicial 'a'
        this.corBrilho = c;
        this.energiaCura = 20; 
    }

    public void mudarCor(String novaCor) throws RoboDesligadoException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " desligado.");
        }
        this.corBrilho = novaCor;
        System.out.println("Robo Fada " + retornarNome() + " mudou a cor do brilho para " + corBrilho);
    }

    public void brilhar() throws RoboDesligadoException {
         if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " desligado.");
        }
        System.out.println("Robo Fada " + retornarNome() + " está brilhando na cor " + corBrilho);
    }

    @Override
    public void executarTarefa(Ambiente ambiente) throws RoboDesligadoException, AcaoNaoPermitidaException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " desligado.");
        }
        brilhar();
        // Tenta curar um robô próximo que esteja desligado
        for(Entidade e : ambiente.getEntidades()){
            if(e instanceof Robo && e != this){
                Robo outroRobo = (Robo) e;
                // Verifica se está próximo e desligado
                if(!outroRobo.estaLigado() && Math.abs(outroRobo.getX() - getX()) <=3 && Math.abs(outroRobo.getY() - getY()) <=3 && Math.abs(outroRobo.getZ() - getZ()) <=3 ){
                    try {
                        curar(outroRobo);
                    } catch(EnergiaCuraException ece){
                        System.out.println(retornarNome() + ": Falha ao curar " + outroRobo.retornarNome() + " - " + ece.getMessage());
                        break; // Para de tentar se não tem energia
                    }
                }
            }
        }
    }

    @Override
    public void exibirPosicao() { 
        super.exibirPosicao(); // Chama o de RoboAereo
        try {
            if(estaLigado()) brilhar();
        } catch (RoboDesligadoException e) { /* não brilha se desligado */ }
    }

    // Implementação de Sensoreavel
    @Override
    public void acionarSensores(Ambiente ambiente) throws RoboDesligadoException {
        super.acionarSensor(ambiente); // Chama o acionador de sensores base
         if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " desligado.");
        }
        System.out.println(retornarNome() + " (Fada) usando sensores para sentir auras mágicas.");
    }

    // Implementação de Curavel
    @Override
    public void curar(Robo alvo) throws EnergiaCuraException, RoboDesligadoException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô Fada " + retornarNome() + " desligado. Não pode curar.");
        }
        if (energiaCura <= 0) {
            throw new EnergiaCuraException("Robo Fada " + retornarNome() + " não tem energia suficiente para curar!");
        }
        if (alvo == this) {
             System.out.println("Robo Fada " + retornarNome() + " não pode curar a si mesma.");
             return;
        }
        System.out.println("Robo Fada " + this.retornarNome() + " está curando o robô " + alvo.retornarNome());
        if(!alvo.estaLigado()){ // Exemplo de cura: ligar o robô
            alvo.ligar();
            System.out.println("Robô " + alvo.retornarNome() + " foi reativado pela Fada!");
        }
        energiaCura--;
    }
}