package tech.rodrigo.pedidos_ms.listener.dto;

import java.math.BigDecimal;

public record PedidoItemEvent(String produto,
                              Integer quantidade,
                              BigDecimal preco) {
}
