class Robo {
    //define a classe
    private String nome;
    private int posicaoX;
    private int posicaoY;
//funcao de atribuicao
    public Robo(String nomedorobo, int x, int y){
        nome= nomedorobo;
        posicaoX= x;
        posicaoY = y;
    }
//metodos
    public void mover (int deltaX, int deltaY){
        posicaoX+=deltaX;
        posicaoY+=deltaY;
    }
    public void exibirPosicao(){
        System.out.println(nome + "está na posição("+ posicaoX +", "+ posicaoY +")");
    }

}