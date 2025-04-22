public class SensorProximidade extends Sensor{
    public SensorProximidade(double raio){
        super(raio);
    }
    @Override
    public void monitorar(Robo robo, Ambiente ambiente){
        int xrobo = robo.retornarX();
        int yrobo = robo.retornarY();
        boolean encontrou = false;

        for (Robo outro : ambiente.getRobosnoespaco()){ //percorre a lista de robos
            if (outro != robo && !(outro instanceof RoboAereoFantasma)){ //verifica se o robo nao é ele mesmo, robos fantasmas tambem nao sao identificados
                int xoutro = outro.retornarX();
                int youtro = outro.retornarY();
                double distancia = Math.sqrt(Math.pow(xrobo - xoutro, 2) + Math.pow(yrobo - youtro, 2)); 
                
                if (distancia <= getRaio()){ //ve se esta no raio do sensor
                    System.out.printf("Robô %s detactado a %.2f cm de distância do robô %s\n", outro.retornarNome(), distancia, robo.retornarNome());
                    encontrou = true;
                }
            }
        }
        for (Obstaculo obstaculo : ambiente.getObstaculos()){ //percorre os obstaculos do espaco e faz a mesma coisa
            int xobstaculo = obstaculo.getX();
            int yobstaculo = obstaculo.getY();
            double distancia = Math.sqrt(Math.pow(xrobo - xobstaculo, 2) + Math.pow(yrobo - yobstaculo, 2));
            
            if (distancia <= getRaio()){
                System.out.printf("Obstáculo %s detectado a %.2f cm de distância do robô %s\n", obstaculo.getTipo(), distancia, robo.retornarNome());
                encontrou = true;
            }
        }

        if (!encontrou){
            System.out.printf("Robô %s não detectou robôs nem obstáculos próximos\n", robo.retornarNome());
        }
    }
}