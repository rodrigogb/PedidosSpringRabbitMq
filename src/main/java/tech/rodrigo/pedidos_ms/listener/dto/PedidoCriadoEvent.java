package tech.rodrigo.pedidos_ms.listener.dto;

import java.util.List;

public record PedidoCriadoEvent(Long codigoPedido,
                                Long codigoCliente,
                                List<PedidoItemEvent> itens) {
}
