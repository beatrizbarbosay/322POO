public class RoboAereo extends Robo{ //cria a classe RoboAereo que herda de Robo
    private int altitude;
    private int altitudemax;

    public RoboAereo(String n, int x, int y, String d, int a, int am) { // construtor herdando de Robo
        super(n, x, y, d);
        altitude = a;
        altitudemax = am;
    }

    public void subir(int metros) { //cria o método subir
        if(altitude+metros<= altitudemax){ //verifica se a altitude é menor que a altitude máxima
            altitude += metros;
        }else{
            altitude = altitudemax;//se for maior, a altitude é igual a altitude máxima
        }
        System.out.println("Robo " + retornarNome() + " subiu para a altitude " + altitude);
    }

    public void descer(int metros) {//
        if(altitude-metros>=0){//verifica se a altitude é maior que 0
            altitude -= metros;
        }else{
            altitude = 0;//se for menor, a altitude é igual a 0
        }
        system.out.println("Robo " + retornarNome() + " desceu para a altitude " + altitude);
    }
    public int getAltitude(){ //
        return altitude;//retorna a altitude
    }
    public int getAltitudeMax(){
        return altitudemax;
    }

    @Override
    public void exibirPosicao(){ //sobrescreve o método exibirPosicao
        super.exibirPosicao();
        System.out.println("Robo " + retornarNome() + " está na altitude " + altitude);
    }

    @Override
    public void identificarObstaculo(Robo outroRobo) { //sobrescreve o método identificarObstaculo
        if (outroRobo instanceof RoboAereoFantasma) { //verifica se o outroRobo é uma instância de RoboAereoFantasma
            System.out.println("Robo " + retornarNome() + " não detecta obstáculos fantasmas.");
        } else {
            System.out.println("Robo " + retornarNome() + " detectou um obstáculo na posição (" + outroRobo.retornarX() + ", " + outroRobo.retornarY() + ")");
        }
    }
    
}
