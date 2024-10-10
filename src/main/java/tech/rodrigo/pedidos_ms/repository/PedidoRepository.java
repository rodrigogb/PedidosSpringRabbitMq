package tech.rodrigo.pedidos_ms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import tech.rodrigo.pedidos_ms.entity.PedidoEntity;

public interface PedidoRepository extends MongoRepository<PedidoEntity, Long> {

    Page<PedidoEntity> findAllByClienteId(Long clienteId, PageRequest pageRequest);

}
