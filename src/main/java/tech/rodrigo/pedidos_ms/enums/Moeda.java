package tech.rodrigo.pedidos_ms.enums;

public enum Moeda {

    REAL("R$", "Real", 1.0),
    DOLAR("US$", "Dólar", 5.2),
    EURO("€", "Euro", 5.8);

    private final String simbolo;
    private final String nome;
    private final double valor;

    Moeda(String simbolo, String nome, double valor) {
        this.simbolo = simbolo;
        this.nome = nome;
        this.valor = valor;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }
}
