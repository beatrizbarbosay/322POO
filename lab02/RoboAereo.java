// Classe que herda de Robo e representa robôs aéreos
public class RoboAereo extends Robo{
    private int altitude; // Altitude atual do robô aéreo
    private int altitudemax; // Altitude máxima que o robô aéreo pode atingir

    // Construtor que recebe o nome, posição, direção, velocidade e altitude máxima
    public RoboAereo(String n, int x, int y, String d, int a, int am) {
        super(n, x, y, d); // Chama o construtor da classe Robo
        altitude = a; // Inicializa a altitude do robô
        altitudemax = am; // Define a altitude máxima do robô
    }

    // Método para subir o robô a uma certa altura
    public void subir(int metros) {
        if(altitude + metros<= altitudemax){ // Verifica se a altitude é menor que a altitude máxima
            altitude += metros; // Aumenta a altitude
        }else{
            altitude = altitudemax;// Se for maior, o robô fica na altitude máxima
        }
        System.out.println("Robô " + retornarNome() + " subiu para a altitude " + altitude);
    }

    // Método para descer o robô a uma certa altura
    public void descer(int metros) {//
        if(altitude-metros>=0){// Verifica se a altitude é maior que 0 (altitude mínima)
            altitude -= metros;
        }else{
            altitude = 0;// Se for menor, o robô fica na altitude 0 (mínima)
        }
        System.out.println("Robô " + retornarNome() + " desceu para a altitude " + altitude);
    }

    //Retorna a altitude do robô
    public int getAltitude(){ //
        return altitude;
    }

    //Retorna a altitude máxima
    public int getAltitudeMax(){
        return altitudemax;
    }

    @Override
    public void exibirPosicao(){ //sobrescreve o método exibirPosicao
        System.out.println("Robô " + retornarNome() + " está na posição " + "(" + retornarX() + ", " + retornarY() + ", " + altitude + ")");
    }

    @Override
    public void identificarObstaculo(Robo outroRobo) { // Sobrescreve o método identificarObstaculo
        if (outroRobo instanceof RoboAereoFantasma) { // Verifica se o outroRobo é uma instância de RoboAereoFantasma
            System.out.println("Robo " + retornarNome() + " não detecta obstáculos fantasmas.");
        } else {
            System.out.println("Robo " + retornarNome() + " detectou um obstáculo na posição (" + outroRobo.retornarX() + ", " + outroRobo.retornarY() + ")");
        }
    }
    
}