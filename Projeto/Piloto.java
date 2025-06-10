//Representa um piloto
public class Piloto {
    //Atributos
    private String nome; //Nome
    private int idade; //Idade
    private String nacionalidade; //Nacionalidade
    private Sexo sexo; //Sexo

    //Método construtor
    public Piloto(String nome, int idade, String nacionalidade, Sexo sexo) {
        this.nome = nome;
        this.idade = idade;
        this.nacionalidade = nacionalidade;
        this.sexo = sexo;
    }

    //Métodos getters
    public String getNome() {
        return nome;
    }
    public int getIdade() {
        return idade;
    }
    public String getNacionalidade() {
        return nacionalidade;
    }
    public Sexo getSexo() {
        return sexo;
    }

    @Override
    //Exibe informações do piloto
    public String toString() {
        return String.format(
            "%s | Sexo: %s | Idade: %d anos | Nacionalidade: %s",
            nome, sexo, idade, nacionalidade
        );
    }
}