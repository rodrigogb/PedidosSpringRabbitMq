package tech.rodrigo.pedidos_ms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.rodrigo.pedidos_ms.entity.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

}
