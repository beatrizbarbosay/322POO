import java.util.ArrayList;
import java.util.List;

//Representa a combinação de um piloto com um carro
public class DuplaCorrida {
    private Piloto piloto;
    private Carro carro;
    private List<Double> tempos;

    public DuplaCorrida(Piloto piloto, Carro carro) {
        this.piloto = piloto;
        this.carro = carro;
        this.tempos = new ArrayList<>();
    }

    public void adicionarTempo(double tempo) {
        tempos.add(tempo);
    }

    public double getTempoMedio() {
        if (tempos.isEmpty()) return 0;
        double soma = 0;
        for (double t : tempos) soma += t;
        return soma / tempos.size();
    }

    public Piloto getPiloto() { return piloto; }
    public Carro getCarro() { return carro; }

    @Override
    //Exibe informações da dupla
    public String toString() {
        return piloto.getNome() + " com " + carro.getModelo();
    }
}