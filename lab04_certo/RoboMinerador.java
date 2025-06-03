import java.util.ArrayList;

class RoboMinerador extends RoboTerrestre implements Sensoreavel, Memorizavel, Comunicavel {
    private String tipoMineral; 
    private ArrayList<String> memoria = new ArrayList<>(); 

    public RoboMinerador(String n, int x, int y, String d, int v, String m) {
        super(n, x, y, d, v);
        this.tipoMineral = m;
    }

    // Método original
    public void minerar(Ambiente ambiente) throws RoboDesligadoException, AcaoNaoPermitidaException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " desligado. Não pode minerar.");
        }
        if (ambiente.existeMineral(retornarX(), retornarY(), getZ(), tipoMineral)) { 
            System.out.println("Robo " + retornarNome() + " minerou " + tipoMineral + " na posição (" + retornarX() + ", " + retornarY() + ", " + getZ() + ")");
            ambiente.removerMineral(retornarX(), retornarY(), getZ());
            memorizar("Minerou " + tipoMineral, retornarX(), retornarY());
        } else {
            // System.out.println("Robo " + retornarNome() + " não encontrou " + tipoMineral + " para minerar na posição (" + retornarX() + ", " + retornarY() + ", " + getZ() + ")");
            throw new AcaoNaoPermitidaException("Nenhum mineral " + tipoMineral + " encontrado em (" + retornarX() + "," + retornarY() + "," + getZ() +")");
        }
    }

    @Override
    public void executarTarefa(Ambiente ambiente) throws RoboDesligadoException, AcaoNaoPermitidaException {
        minerar(ambiente);
    }

    // Implementação de Sensoreavel
    @Override
    public void acionarSensores(Ambiente ambiente) throws RoboDesligadoException {
        super.acionarSensor(ambiente); // Chama o acionador de sensores base da classe Robo
        if (!estaLigado()) { 
            throw new RoboDesligadoException("Robô " + retornarNome() + " está desligado.");
        }
        System.out.println(retornarNome() + " (Minerador) usando sensores para buscar " + tipoMineral + ".");
    }
    // getSensores() é herdado de Robo.

    @Override
    public void memorizar(String evento, int x, int y) {
        String registro = "Evento: " + evento + " na posição (" + x + ", " + y + ", Z:" + getZ() + ")"; // Adicionado Z para contexto
        memoria.add(registro);
        System.out.println("Robo " + this.retornarNome() + " memorizou: " + registro);
    }

    public void exibirMemoria() {
        System.out.println("Memória do robô " + this.retornarNome() + ":");
        if (memoria.isEmpty()) {
            System.out.println(" (Memória vazia)");
        } else {
            for (String dado : memoria) {
                System.out.println(" - " + dado);
            }
        }
    }
    
    // Implementação de Comunicavel
    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem, CentralComunicacao central)
            throws RoboDesligadoException, ErroComunicacaoException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " (Minerador) está desligado.");
        }
        if (destinatario == null) {
            throw new ErroComunicacaoException("Destinatário da mensagem é nulo.");
        }
        if (!(destinatario instanceof Robo)) { 
             throw new ErroComunicacaoException("Destinatário não é um Robô ou não pode ser identificado.");
        }
        String idDestinatario = ((Robo) destinatario).retornarNome(); 

        System.out.println("Robô " + retornarNome() + " (Minerador) enviou para " + idDestinatario + ": " + mensagem);
        central.registrarMensagem(this.retornarNome(), mensagem);
        destinatario.receberMensagem(mensagem, this.retornarNome());
    }

    @Override
    public void receberMensagem(String mensagem, String idRemetente) throws RoboDesligadoException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " (Minerador) está desligado.");
        }
        System.out.println("Robô " + retornarNome() + " (Minerador) recebeu de " + idRemetente + ": " + mensagem);
        memorizar("Recebeu msg de " + idRemetente + ": " + mensagem.substring(0, Math.min(10, mensagem.length()))+"...", retornarX(), retornarY());
    }
}