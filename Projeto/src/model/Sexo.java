package model;

public enum Sexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    OUTRO("Outro");

    private String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}