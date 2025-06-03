public class SensorProximidade extends Sensor{ //esse sensor identifica outros robos e outros obstaculos em seu raio, nao identifica robos fantasmas
    public SensorProximidade(double raio){
        super(raio);
    }
    @Override

    public String getTipoSensor() {
        return "Sensor de Proximidade";
    }
    public void monitorar(Robo robo, Ambiente ambiente) {
        int xrobo = robo.retornarX();
        int yrobo = robo.retornarY();
        int zrobo = robo.getZ(); // considerar também o Z
        boolean encontrou = false;

        for (Entidade entidade : ambiente.getEntidades()) {
            if (entidade instanceof Robo outroRobo && outroRobo != robo && !(outroRobo instanceof RoboAereoFantasma)) {
                if (outroRobo.getZ() != zrobo) continue; // ignorar se estiverem em andares diferentes
                int xoutro = outroRobo.retornarX();
                int youtro = outroRobo.retornarY();
                double distancia = Math.sqrt(Math.pow(xrobo - xoutro, 2) + Math.pow(yrobo - youtro, 2));

                if (distancia <= getRaio()) {
                    System.out.printf("Robô %s detectado a %.2f cm de distância do robô %s\n", outroRobo.retornarNome(), distancia, robo.retornarNome());
                    encontrou = true;
                }
            } else if (entidade instanceof Obstaculo obstaculo && obstaculo.getZ() == zrobo) {
                int xobstaculo = obstaculo.getX();
                int yobstaculo = obstaculo.getY();
                double distancia = Math.sqrt(Math.pow(xrobo - xobstaculo, 2) + Math.pow(yrobo - yobstaculo, 2));

                if (distancia <= getRaio()) {
                    System.out.printf("Obstáculo %s detectado a %.2f cm de distância do robô %s\n", obstaculo.getTipo(), distancia, robo.retornarNome());
                    encontrou = true;
                }
            }
        }

        if (!encontrou) {
            System.out.printf("Robô %s não detectou robôs nem obstáculos próximos\n", robo.retornarNome());
        }
    }

}