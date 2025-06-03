public enum TipoObstaculo{ //tipos de obstaculos possiveis no ambiente
    Parede(3, true, false, false),
    Arvore(5, true, false, false),
    Predio(10, true, false, false),
    Buraco(0, true, true, false),
    Cachoeira(3, false, false, true),
    Vulcao(15, true, true, false),
    Fogo(3, false, true, false),
    Gelo(0, false, false, true);

    private final int alturaPadrao;
    private final boolean bloqueiaPassagem;
    private final boolean ehquente; //essas caracteristicas serao usadas pelo sensor de temperatura
    private final boolean ehfrio;

    TipoObstaculo(int alturaPadrao, boolean bloqueiaPassagem, boolean ehquente, boolean ehfrio) {
        this.alturaPadrao = alturaPadrao;
        this.bloqueiaPassagem = bloqueiaPassagem;
        this.ehquente = ehquente;
        this.ehfrio = ehfrio;
    }

    public int getAlturaPadrao() {
        return alturaPadrao;
    }

    public boolean isBloqueiaPassagem() {
        return bloqueiaPassagem;
    }

    public boolean isEhquente() {
        return ehquente;
    }

    public boolean isEhfrio() {
        return ehfrio;
    } 
}  
