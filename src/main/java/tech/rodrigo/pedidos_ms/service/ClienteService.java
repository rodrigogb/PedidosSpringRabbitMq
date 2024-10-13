package tech.rodrigo.pedidos_ms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import tech.rodrigo.pedidos_ms.config.RabbitMqConfig;
import tech.rodrigo.pedidos_ms.entity.Cliente;
import tech.rodrigo.pedidos_ms.repository.ClienteRepository;

@Service
public class ClienteService {

    private final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    private ClienteRepository clienteRepository;
    private final RabbitTemplate rabbitTemplate;

    public ClienteService(ClienteRepository clienteRepository, RabbitTemplate rabbitTemplate) {
        this.clienteRepository = clienteRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void save(Cliente cliente) {

        clienteRepository.save(cliente);

        if (cliente.getNome().equals("física")) {
            String message = "Cliente PF: " + cliente.getNome() + " - Documento: " + cliente.getDocumento();
            rabbitTemplate.convertAndSend(RabbitMqConfig.CLIENTES_PF_QUEUE, message);
            logger.info("Mensagem Enviada para fila PF: {}", message);
        } else if (cliente.getNome().equals("jurídica")) {
            String message = "Cliente PJ: " + cliente.getNome() + " - Documento: " + cliente.getDocumento();
            rabbitTemplate.convertAndSend(RabbitMqConfig.CLIENTES_PJ_QUEUE, message);
            logger.info("Mensagem Enviada para fila PJ: {}", message);
        }
    }
}
