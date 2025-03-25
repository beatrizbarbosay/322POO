public class RoboAereoFada extends RoboAereo { //subclasse de robo aereo que muda a cor do brilho 
    private String corBrilho; //cor atual
        
    public RoboAereoFada(String n, int x, int y, String d, int a, int am, String c) {        
        super(n, x, y, d, a, am);
        corBrilho = c;
    }

    public void mudarCor(String novaCor) { //muda a cor do brilho
        corBrilho = novaCor;
        System.out.println("Robo Fada " + retornarNome() + " mudou a cor do brilho para " + corBrilho);
    }
    public void brilhar() { //brilha
        System.out.println("Robo Fada " + retornarNome() + " está brilhando na cor " + corBrilho);
    }

    @Override
    public void exibirPosicao() { //exibe a cor que ela esta brilhando e a altitude
        super.exibirPosicao();
        brilhar();
        System.out.println("Robo Fada " + retornarNome() + " está na altitude " + getAltitude());
    }

    
}
