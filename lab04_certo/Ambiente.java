import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ambiente {
    private int largura; // Eixo X
    private int altura;  // Eixo Y 
    private int profundidade; 
    
    // private HashMap<String, String> minerais;

    private ArrayList<Entidade> entidades;
    private TipoEntidade[][][] mapa;
    private List<int[]> colisoesVisuais = new ArrayList<>(); // Para visualização de colisões detectadas

    public Ambiente(int x, int y, int z) { // x: largura, y: altura(Y), z: profundidade(Z)
        this.largura = x;
        this.altura = y;
        this.profundidade = z;
        // this.minerais = new HashMap<>(); 
        
        this.entidades = new ArrayList<>();
        this.mapa = new TipoEntidade[largura][altura][profundidade]; // X, Y, Z
        inicializarMapa();
    }

    public void inicializarMapa() {
        for (int i = 0; i < largura; i++) {      // X
            for (int j = 0; j < altura; j++) {   // Y
                for (int k = 0; k < profundidade; k++) { // Z
                    mapa[i][j][k] = TipoEntidade.VAZIO;
                }
            }
        }
    }

    public void registrarColisaoVisual(int x, int y, int z) {
        colisoesVisuais.add(new int[]{x, y, z});
    }

    public void adicionarEntidade(Entidade e) throws ForaDosLimitesException, ColisaoException {
        dentroDosLimites(e.getX(), e.getY(), e.getZ()); // Verifica limites primeiro
        if (estaOcupado(e.getX(), e.getY(), e.getZ())) {
            throw new ColisaoException("Erro ao adicionar entidade " + e.getDescricao() + ": posição ("+e.getX()+","+e.getY()+","+e.getZ()+") ocupada.");
        }
        entidades.add(e);
        mapa[e.getX()][e.getY()][e.getZ()] = e.getTipo();
    }

    public void removerEntidade(Entidade e) {
        if(entidades.remove(e)){
            try {
                dentroDosLimites(e.getX(), e.getY(), e.getZ()); // Checa se estava dentro antes de limpar mapa
                mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;
            } catch (ForaDosLimitesException ex){
            }
        }
    }

    public void dentroDosLimites(int x, int y, int z) throws ForaDosLimitesException {
        if (x < 0 || x >= largura || y < 0 || y >= altura || z < 0 || z >= profundidade) {
            throw new ForaDosLimitesException("Posição (" + x + ", " + y + ", " + z + ") está fora dos limites do ambiente.");
        }
    }

    public boolean estaOcupado(int x, int y, int z) throws ForaDosLimitesException {
        dentroDosLimites(x, y, z); // Garante que a consulta é válida
        return mapa[x][y][z] != TipoEntidade.VAZIO;
    }
    
    private boolean isMovingToItsOwnSpot(Entidade e, int novoX, int novoY, int novoZ) {
        for(Entidade entNoDestino : getEntidadesNaPosicao(novoX, novoY, novoZ)) {
            if(entNoDestino == e) return true;
        }
        return false;
    }

    public void moverEntidade(Entidade e, int novoX, int novoY, int novoZ)
            throws ColisaoException, ForaDosLimitesException, RoboDesligadoException, AcaoNaoPermitidaException {
        
        dentroDosLimites(novoX, novoY, novoZ); // Destino deve ser válido

        if (mapa[novoX][novoY][novoZ] != TipoEntidade.VAZIO && !isMovingToItsOwnSpot(e, novoX, novoY, novoZ)) {
             // Se o mapa diz que está ocupado E não é a própria entidade se "movendo" para o mesmo lugar
            throw new ColisaoException("Movimento bloqueado para (" + novoX + "," + novoY + "," + novoZ + "): destino ocupado por outra entidade no mapa.");
        }

        int antigoX = e.getX();
        int antigoY = e.getY();
        int antigoZ = e.getZ();

        if (e instanceof Robo) {
            ((Robo) e).moverPara(novoX, novoY, novoZ, this); // Robô atualiza seu estado interno
        } else {
            throw new AcaoNaoPermitidaException("Apenas Robos podem ser movidos ativamente pelo Ambiente desta forma.");
        }
        
        // Atualiza o mapa do ambiente se a posição realmente mudou
        if (antigoX != novoX || antigoY != novoY || antigoZ != novoZ) {
            try {
                 dentroDosLimites(antigoX, antigoY, antigoZ); // Só limpa se a pos antiga era válida
                 if (mapa[antigoX][antigoY][antigoZ] == e.getTipo()) { // E se era realmente da entidade
                    mapa[antigoX][antigoY][antigoZ] = TipoEntidade.VAZIO;
                 }
            } catch (ForaDosLimitesException ex) { /* não faz nada, estava fora */ }
           
            mapa[novoX][novoY][novoZ] = e.getTipo();
        }
        // System.out.println(e.getDescricao() + " agora está em (" + novoX + "," + novoY + "," + novoZ + ")"); // O robo.moverPara já pode logar
    }
    
    public ArrayList<Entidade> getEntidadesNaPosicao(int x, int y, int z) {
        ArrayList<Entidade> encontradas = new ArrayList<>();
        for (Entidade ent : entidades) {
            if (ent.getX() == x && ent.getY() == y && ent.getZ() == z) {
                encontradas.add(ent);
            }
        }
        return encontradas;
    }

    public void executarTodosOsSensores() {
        System.out.println("\n--- Ambiente executando sensores ---");
        for (Entidade e : entidades) {
            if (e instanceof Sensoreavel) {
                try {
                    System.out.print("Para " + e.getDescricao() + ": ");
                    ((Sensoreavel) e).acionarSensores(this);
                } catch (RoboDesligadoException rde) {
                    System.out.println("Falha ao acionar sensor: " + rde.getMessage());
                }
            }
        }
        System.out.println("--- Fim da execução de sensores ---\n");
    }

    public void verificarColisoes() throws ColisaoException { 
        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                for (int z = 0; z < profundidade; z++) {
                    if (getEntidadesNaPosicao(x,y,z).size() > 1) {
                        registrarColisaoVisual(x,y,z); // Registra para o mapa visual
                        throw new ColisaoException("Colisão múltipla detectada na posição (" + x + "," + y + "," + z + ")");
                    }
                }
            }
        }
    }

    public ArrayList<Entidade> getEntidades() {
        return entidades;
    }
    
    public ArrayList<Robo> getRobos() { 
        ArrayList<Robo> listaRobos = new ArrayList<>();
        for (Entidade e : entidades) {
            if (e instanceof Robo) {
                listaRobos.add((Robo) e);
            }
        }
        return listaRobos;
    }
    
    public Robo getRoboPorNome(String nome) { 
        for (Robo r : getRobos()) {
            if (r.retornarNome().equalsIgnoreCase(nome)) {
                return r;
            }
        }
        return null;
    }
    
    public void visualizarAmbiente() { 
        visualizarAmbienteNoPlanoZ(0);
    }

    public void visualizarAmbienteNoPlanoZ(int zLayer) {
        System.out.println("Mapa do ambiente (Plano XY, Camada Z = " + zLayer + "):");
        try {
            dentroDosLimites(0,0,zLayer); // Verifica se a camada Z é válida
        } catch(ForaDosLimitesException e){
            System.out.println(e.getMessage());
            return;
        }

        for (int y = 0; y < altura; y++) { // Y (linhas)
            for (int x = 0; x < largura; x++) { // X (colunas)
                char c = '.'; 
                boolean colisaoVisualizada = false;
                for (int[] pos : colisoesVisuais) { // Usa a lista de colisões visuais
                    if (pos[0] == x && pos[1] == y && pos[2] == zLayer) {
                        c = '*';
                        colisaoVisualizada = true;
                        break;
                    }
                }

                if (!colisaoVisualizada) {
                    ArrayList<Entidade> entsAqui = getEntidadesNaPosicao(x,y,zLayer);
                    if (!entsAqui.isEmpty()){
                        if(entsAqui.size() > 1) c = '#'; // Múltiplas entidades na mesma célula
                        else c = entsAqui.get(0).getRepresentacao();
                    }
                }
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public void visualizarAmbienteComDestaque(Robo roboSelecionado) {
        if (roboSelecionado == null) {
            visualizarAmbiente();
            return;
        }
        int zLayerRobo = roboSelecionado.getZ();
        System.out.println("Mapa do ambiente (Camada Z = " + zLayerRobo + "), Robô selecionado: [" + roboSelecionado.retornarNome() + "]");
         try {
            dentroDosLimites(0,0,zLayerRobo);
        } catch(ForaDosLimitesException e){
            System.out.println("Robô selecionado está em uma camada Z inválida: " + e.getMessage());
            visualizarAmbiente(); 
            return;
        }

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                boolean ehColisao = false;
                for (int[] cVis : colisoesVisuais) {
                    if (cVis[0] == x && cVis[1] == y && cVis[2] == zLayerRobo) {
                        ehColisao = true;
                        break;
                    }
                }

                char charSimbolo = '.';
                ArrayList<Entidade> entsAqui = getEntidadesNaPosicao(x,y,zLayerRobo);
                 if (!entsAqui.isEmpty()){
                    if(entsAqui.size() > 1) charSimbolo = '#';
                    else charSimbolo = entsAqui.get(0).getRepresentacao();
                }

                if (ehColisao) {
                    System.out.print("[*] "); // Colisão visualizada
                } else if (roboSelecionado.getX() == x && roboSelecionado.getY() == y && roboSelecionado.getZ() == zLayerRobo) {
                    System.out.print("[" + charSimbolo + "] "); // Robô selecionado
                } else {
                    System.out.print(" " + charSimbolo + "  ");
                }
            }
            System.out.println();
        }
    }

    private HashMap<String, String> mapaDeMinerais = new HashMap<>();

    public void adicionarMineral(int x, int y, String tipoMineral) { 
        this.adicionarMineral(x,y,0, tipoMineral); 
    }
    public void adicionarMineral(int x, int y, int z, String tipoMineral) {
        String chave = x + "," + y + "," + z;
        mapaDeMinerais.put(chave, tipoMineral);
    }

    public boolean existeMineral(int x, int y, String tipoMineral) { 
        return this.existeMineral(x,y,0,tipoMineral);
    }
    public boolean existeMineral(int x, int y, int z, String tipoMineral) {
        String chave = x + "," + y + "," + z;
        return mapaDeMinerais.containsKey(chave) && mapaDeMinerais.get(chave).equals(tipoMineral);
    }

    public void removerMineral(int x, int y) { 
        this.removerMineral(x,y,0);
    }
    public void removerMineral(int x, int y, int z) {
        String chave = x + "," + y + "," + z;
        mapaDeMinerais.remove(chave);
    }

    public ArrayList<Obstaculo> getObstaculos() { 
        ArrayList<Obstaculo> listaObstaculos = new ArrayList<>();
        for (Entidade e : entidades) {
            if (e instanceof Obstaculo) {
                listaObstaculos.add((Obstaculo) e);
            }
        }
        return listaObstaculos;
    }
    public int getLargura() { return largura; }
    public int getAltura() { return altura; } 
    public int getProfundidade() { return profundidade; } 
}