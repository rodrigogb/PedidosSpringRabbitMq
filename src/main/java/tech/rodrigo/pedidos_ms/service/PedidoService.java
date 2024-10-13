package tech.rodrigo.pedidos_ms.service;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import tech.rodrigo.pedidos_ms.controller.dto.PedidoResponseDTO;
import tech.rodrigo.pedidos_ms.entity.Pedido;
import tech.rodrigo.pedidos_ms.entity.PedidoItem;
import tech.rodrigo.pedidos_ms.listener.dto.PedidoCriadoEvent;
import tech.rodrigo.pedidos_ms.repository.PedidoRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final MongoTemplate mongoTemplate;

    public PedidoService(PedidoRepository pedidoRepository, MongoTemplate mongoTemplate) {
        this.pedidoRepository = pedidoRepository;
        this.mongoTemplate = mongoTemplate;
    }
    public void save(PedidoCriadoEvent event) {
        var entity = new Pedido();
        entity.setPedidoId(event.codigoPedido());
        entity.setClienteId(event.codigoCliente());
        entity.setItens(getPedidoItens(event));
        entity.setTotal(getTotal(event));

        pedidoRepository.save(entity);
    }

    public Page<PedidoResponseDTO> findAllByClienteId(Long clienteId, PageRequest pageRequest) {
        var orders = pedidoRepository.findAllByClienteId(clienteId, pageRequest);
        return orders.map(PedidoResponseDTO::fromEntity);
    }

    public BigDecimal findTotalOnPedidosByClienteId(Long clienteId) {
        var aggregations = newAggregation(
                match(Criteria.where("clienteId").is(clienteId)),
                group().sum("total").as("total")
        );

        var response = mongoTemplate.aggregate(aggregations, "tb_pedidos", Document.class);

        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
    }

    private BigDecimal getTotal(PedidoCriadoEvent event) {
        return event.itens()
                .stream()
                .map(i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private static List<PedidoItem> getPedidoItens(PedidoCriadoEvent event) {
        return event.itens().stream()
                .map(i -> new PedidoItem(i.produto(), i.quantidade(), i.preco()))
                .toList();
    }
}
