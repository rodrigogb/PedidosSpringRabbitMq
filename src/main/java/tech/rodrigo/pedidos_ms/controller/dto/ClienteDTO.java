package tech.rodrigo.pedidos_ms.controller.dto;

import tech.rodrigo.pedidos_ms.entity.Cliente;
import tech.rodrigo.pedidos_ms.entity.PedidoEntity;

public record ClienteDTO (String nome, String documento) {

    public static Cliente toEntity(ClienteDTO record) {
        return new Cliente(record.nome, record.documento);

    }


}

