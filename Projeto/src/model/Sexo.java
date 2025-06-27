package model;

// Enum que representa os gêneros disponíveis para pilotos.
public enum Sexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    OUTRO("Outro");

    private String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

}