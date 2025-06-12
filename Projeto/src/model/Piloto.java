//Representa um piloto

package model;

import java.io.Serializable;

public class Piloto implements Serializable {
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

    private static String getEmojiBandeira(String nacionalidade) {
        nacionalidade = stripAccents(nacionalidade.trim().toLowerCase());
        return switch (nacionalidade) {
            case "brasil", "brazil" -> "🇧🇷";
            case "estados unidos", "eua", "usa", "united states" -> "🇺🇸";
            case "franca", "france" -> "🇫🇷";
            case "alemanha", "germany" -> "🇩🇪";
            case "japao", "japan" -> "🇯🇵";
            case "italia", "italy" -> "🇮🇹";
            case "espanha", "spain" -> "🇪🇸";
            case "argentina" -> "🇦🇷";
            case "canada" -> "🇨🇦";
            case "reino unido", "united kingdom", "inglaterra", "england" -> "🇬🇧";
            case "china" -> "🇨🇳";
            case "russia" -> "🇷🇺";
            case "india" -> "🇮🇳";
            case "australia" -> "🇦🇺";
            case "mexico" -> "🇲🇽";
            case "coreia do sul", "south korea" -> "🇰🇷";
            case "portugal" -> "🇵🇹";
            case "holanda", "netherlands" -> "🇳🇱";
            case "suecia", "sweden" -> "🇸🇪";
            case "noruega", "norway" -> "🇳🇴";
            case "dinamarca", "denmark" -> "🇩🇰";
            case "finlandia", "finland" -> "🇫🇮";
            case "polonia", "poland" -> "🇵🇱";
            case "suica", "switzerland" -> "🇨🇭";
            case "belgica", "belgium" -> "🇧🇪";
            case "austria" -> "🇦🇹";
            case "grecia", "greece" -> "🇬🇷";
            case "africa do sul", "south africa" -> "🇿🇦";
            case "turquia", "turkey" -> "🇹🇷";
            case "arabia saudita", "saudi arabia" -> "🇸🇦";
            case "israel" -> "🇮🇱";
            case "ucrania", "ukraine" -> "🇺🇦";
            case "tailandia", "thailand" -> "🇹🇭";
            case "nova zelandia", "new zealand" -> "🇳🇿";
            default -> "🏳️"; // Bandeira genérica
        };
    }

    private static String stripAccents(String s) {
        java.text.Normalizer.Form form = java.text.Normalizer.Form.NFD;
        s = java.text.Normalizer.normalize(s, form);
        return s.replaceAll("\\p{M}", "");
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