package tech.rodrigo.pedidos_ms.controller.dto;

import tech.rodrigo.pedidos_ms.entity.Pedido;

import java.math.BigDecimal;

public record PedidoResponseDTO(Long pedidoId,
                                Long clienteId,
                                BigDecimal total) {

    public static PedidoResponseDTO fromEntity(Pedido entity) {
        return new PedidoResponseDTO(entity.getPedidoId(), entity.getClienteId(), entity.getTotal());

    }
}
