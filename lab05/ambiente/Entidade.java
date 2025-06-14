package ambiente;
import ambiente.TipoEntidade;

public interface Entidade {
    int getX();
    int getY();
    int getZ();
    TipoEntidade getTipo();
    String getDescricao();
    char getRepresentacao();
}