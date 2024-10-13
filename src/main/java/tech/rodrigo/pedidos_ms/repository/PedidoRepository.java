package tech.rodrigo.pedidos_ms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import tech.rodrigo.pedidos_ms.entity.Pedido;

public interface PedidoRepository extends MongoRepository<Pedido, Long> {

    Page<Pedido> findAllByClienteId(Long clienteId, PageRequest pageRequest);

}
