public class SensorQuenteFrio extends Sensor {
//esse sensor verifica a posição atual do robô e compara a distância até cada obstáculo relevante
    public SensorQuenteFrio(double raio) {
        super(raio);
    }

    @Override
    public String getTipoSensor() {
        return "Sensor de Temperatura";
    }
    
    public void monitorar(Robo robo, Ambiente ambiente) {
        int xrobo = robo.retornarX();
        int yrobo = robo.retornarY();
        int z = 0;
        if (robo instanceof RoboAereo) {
            z = ((RoboAereo) robo).getAltitude();
        }

        boolean detectouTemperatura = false;

        for (Obstaculo obstaculo : ambiente.getObstaculos()) { //analisa todos os obstaculos do ambiente
            int xobstaculo = obstaculo.getX();
            int yobstaculo = obstaculo.getY();

            double distancia = Math.sqrt(Math.pow(xrobo - xobstaculo, 2) + Math.pow(yrobo - yobstaculo, 2)); //calcula a distancia ao sensor do robo

            TipoObstaculo tipo = obstaculo.getTipoObstaculo();

            if (distancia <= raio) { //verifica a temperatura dos objetivos no raio do sensor
                if (tipo.isEhquente()) {
                    System.out.printf("Temperatura = Quente. %s detectado a uma distância de %.2f cm.%n", tipo.name(), distancia);
                    detectouTemperatura = true;
                } else if (tipo.isEhfrio()) {
                    System.out.printf("Temperatura = Frio. %s detectado a uma distância de %.2f cm.%n",tipo.name(), distancia);
                    detectouTemperatura = true;
                }
            }
        }

        if (!detectouTemperatura) {
            System.out.println("Temperatura = Neutra. Nenhum obstáculo térmico dentro do alcance.");
        }
    }
}
