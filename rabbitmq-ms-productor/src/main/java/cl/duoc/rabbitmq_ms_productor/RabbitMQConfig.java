package cl.duoc.rabbitmq_ms_productor;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    // Nombre del Exchange utilizado para enrutar los mensajes
    public static final String EXCHANGE_NAME = "logs.direct";
    // Nombre de la cola general
    public static final String ALL_LOGS_QUEUE = "all_logs_queue";
    // Nombre de la cola que recibe solamente errores
    public static final String ERRORS_QUEUE = "errors_only_queue";

    // Declara el Exchange de tipo Direct
    @Bean
    public DirectExchange logsExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }


    @Bean
    public Queue allLogsQueue() {
        return new Queue(ALL_LOGS_QUEUE, true);
    }

    @Bean
    public Queue errorsOnlyQueue() {
        return new Queue(ERRORS_QUEUE, true);
    }

    // INFO → all_logs_queue
    @Bean
    public Binding infoBinding() {
        return BindingBuilder
                .bind(allLogsQueue())
                .to(logsExchange())
                .with("INFO");
    }

    // WARNING → all_logs_queue
    @Bean
    public Binding warningBinding() {
        return BindingBuilder
                .bind(allLogsQueue())
                .to(logsExchange())
                .with("WARNING");
    }

    // ERROR → all_logs_queue
    @Bean
    public Binding errorAllLogsBinding() {
        return BindingBuilder
                .bind(allLogsQueue())
                .to(logsExchange())
                .with("ERROR");
    }

    // ERROR → errors_only_queue
    @Bean
    public Binding errorOnlyBinding() {
        return BindingBuilder
                .bind(errorsOnlyQueue())
                .to(logsExchange())
                .with("ERROR");
    }
}