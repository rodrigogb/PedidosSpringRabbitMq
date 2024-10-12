package tech.rodrigo.pedidos_ms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tech.rodrigo.pedidos_ms.config.RabbitMqConfig;

@Component
public class ClientesCriadosListener {

    private final Logger logger = LoggerFactory.getLogger(ClientesCriadosListener.class);

    @RabbitListener(queues = RabbitMqConfig.CLIENTES_PF_QUEUE)
    public void listenPfMessage(String message) {
        logger.info("Mensagem consumida PF: {}", message);
    }

    @RabbitListener(queues = RabbitMqConfig.CLIENTES_PJ_QUEUE)
    public void listenPjMessage(String message) {
        logger.info("Mensagem consumida PJ: {}", message);
    }
}
