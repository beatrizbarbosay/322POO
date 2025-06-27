package robo;
import java.util.ArrayList;

import ambiente.Entidade;
import ambiente.Ambiente;
import ambiente.Obstaculo;
import exceptions.AcaoNaoPermitidaException;
import exceptions.SobreCargaExplosivaException;
import interfaces.Atacante;
import sensores.Sensoreavel; 
import exceptions.EnergiaCuraException;

public class RoboBombardeiro extends RoboTerrestre implements Sensoreavel, Atacante {
    private int quantidadeBombas;
    private int bombasAtivas; 
    private static final int LIMITE_MAXIMO_BOMBAS_NO_ROBO = 10;

    public RoboBombardeiro(String n, int x, int y, String d, int v, int b) {
        super(n, x, y, d, v);
        this.quantidadeBombas = Math.min(b, LIMITE_MAXIMO_BOMBAS_NO_ROBO);
        this.bombasAtivas = 0; // Bombas "no ar" ou recém usadas
    }

    public void plantarBombaNoLocal() throws RoboDesligadoException, AcaoNaoPermitidaException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " está desligado.");
        }
        if (quantidadeBombas > 0) {
            System.out.println("Robô " + retornarNome() + " plantou uma bomba na posição (" + getX() + ", " + getY() + ", " + getZ() + ").");
            quantidadeBombas--;
            // ambiente.adicionarEntidade(new Bomba(getX(), getY(), getZ()));
        } else {
            throw new AcaoNaoPermitidaException("Robô " + retornarNome() + " sem bombas para plantar.");
        }
    }

    @Override
    public void executarTarefa(Ambiente ambiente) throws RoboDesligadoException, AcaoNaoPermitidaException, SobreCargaExplosivaException, EnergiaCuraException {
        // Tenta atacar um alvo aleatório ou plantar bomba se não houver alvos.
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " está desligado.");
        }
        System.out.println("Robô Bombardeiro " + retornarNome() + " procurando alvos...");
        ArrayList<Robo> robosNoAmbiente = ambiente.getRobos();
        Robo alvoParaAtacar = null;
        for(Robo r : robosNoAmbiente){
            if(r != this && Math.abs(r.getX() - getX()) < 5 && Math.abs(r.getY() - getY()) < 5 && r.getZ() == getZ()){ // Alvo próximo no mesmo Z
                alvoParaAtacar = r;
                break;
            }
        }
        try {
            if(alvoParaAtacar != null){
                atacar(alvoParaAtacar.getX(), alvoParaAtacar.getY(), ambiente);
            } else {
                plantarBombaNoLocal();
            }
        } catch (SobreCargaExplosivaException e) {
            System.out.println(retornarNome() + " não pode atacar/plantar bomba: " + e.getMessage());
        }
    }
            

    // Implementação de Atacante
    @Override
    public void atacar(int xAlvo, int yAlvo, Ambiente ambiente) throws SobreCargaExplosivaException, RoboDesligadoException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô " + retornarNome() + " está desligado.");
        }
        final int LIMITE_CONCOMITANTE = 2; // Limite de bombas que podem ser "ativas" ao mesmo tempo.
        if (bombasAtivas >= LIMITE_CONCOMITANTE) { 
            throw new SobreCargaExplosivaException("O robô bombardeiro " + retornarNome() + " já tem " + bombasAtivas + " ataques/bombas ativas!");
        }
        if (quantidadeBombas <= 0) {
            System.out.println("Robô " + retornarNome() + " sem bombas para o ataque!");
            return; 
        }

        System.out.println("Robô " + retornarNome() + " lançou um ataque de bomba na posição (" + xAlvo + ", " + yAlvo + ", Z:" + getZ() + ")!");
        quantidadeBombas--;
        bombasAtivas++; // Incrementa contador de "bombas em uso"

        for (Entidade entidadeAlvo : ambiente.getEntidadesNaPosicao(xAlvo, yAlvo, getZ())) { // Ataque no mesmo nível Z do Bombardeiro
            if (entidadeAlvo == this) continue; // Não se atinge

            if (entidadeAlvo instanceof Robo) {
                System.out.println("Robô " + retornarNome() + " atingiu o robô " + ((Robo) entidadeAlvo).retornarNome() + "!");
                // ambiente.removerEntidade(entidadeAlvo); // Exemplo de efeito
            } else if (entidadeAlvo instanceof Obstaculo){
                 System.out.println("Robô " + retornarNome() + " atingiu o obstáculo " + entidadeAlvo.getDescricao() + "!");
                 // ambiente.removerEntidade(entidadeAlvo); // Exemplo de efeito
            }
        }
        new Thread(() -> {
            try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
            bombasAtivas--;
        }).start();
    }
}