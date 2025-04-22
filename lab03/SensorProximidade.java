public class SensorProximidade extends Sensor{
    public SensorProximidade(double raio){
        super(raio);
    }
    @Override
    public void monitorar(Robo robo, Ambiente ambiente){
        int xrobo = robo.retornarX();
        int yrobo = robo.retornarY();
        boolean encontrou = false;

        for (Robo outro : ambiente.getRobosnoespaco()){
            if (outro != robo && !(outro instanceof RoboAereoFantasma)){
                int xoutro = outro.retornarX();
                int youtro = outro.retornarY();
                double distancia = Math.sqrt(Math.pow(xrobo - xoutro, 2) + Math.pow(yrobo - youtro, 2));
                
                if (distancia <= raio()){
                    System.out.printf("Robô %s detactado a %.2f cm de distância do robô %s\n", outro.retornarNome(), distancia, robo.retornarNome());
                    encontrou = true;
                }
            }
        }
        for (Obstaculo obstaculo : ambiente.getObstaculos()){
            int xobstaculo = obstaculo.getX();
            int yobstaculo = obstaculo.getY();
            double distancia = Math.sqrt(Math.pow(xrobo - xobstaculo, 2) + Math.pow(yrobo - yobstaculo, 2));
            
            if (distancia <= raio()){
                System.out.printf("Obstáculo %s detectado a %.2f cm de distância do robô %s\n", obstaculo.retornarNome(), distancia, robo.retornarNome());
                encontrou = true;
            }
        }

        if (!encontrou){
            System.out.printf("Robô %s não detectou robôs nem obstáculos próximos\n", robo.retornarNome());
        }
    }
}