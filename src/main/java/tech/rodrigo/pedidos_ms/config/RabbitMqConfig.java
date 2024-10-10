package tech.rodrigo.pedidos_ms.config;

import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String PEDIDOS_CRIADOS_QUEUE = "pedidos-criados";

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Declarable pedidosCriadosQueue() {
        return new Queue(PEDIDOS_CRIADOS_QUEUE);

    }
}
