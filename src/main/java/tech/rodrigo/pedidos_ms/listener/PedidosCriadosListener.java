package tech.rodrigo.pedidos_ms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import tech.rodrigo.pedidos_ms.listener.dto.PedidoCriadoEvent;
import tech.rodrigo.pedidos_ms.service.PedidoService;

import static tech.rodrigo.pedidos_ms.config.RabbitMqConfig.PEDIDOS_CRIADOS_QUEUE;

@Component
public class PedidosCriadosListener {

    private final Logger logger = LoggerFactory.getLogger(PedidosCriadosListener.class);
    private final PedidoService pedidoService;

    public PedidosCriadosListener(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @RabbitListener(queues = PEDIDOS_CRIADOS_QUEUE)
    public void listen(Message<PedidoCriadoEvent> message) {
        logger.info("Mensagem consumida: {}", message);
        pedidoService.save(message.getPayload());
    }
}
