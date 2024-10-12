package tech.rodrigo.pedidos_ms.enums;

public enum TipoCliente {

    PF(1, "Pessoa Física"),
    PJ(2, "Pessoa Jurídica");

    private final int codigo;
    private final String descricao;

    TipoCliente(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

}
