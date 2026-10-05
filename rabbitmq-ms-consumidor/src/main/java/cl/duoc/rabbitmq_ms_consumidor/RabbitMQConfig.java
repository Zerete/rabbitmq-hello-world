package cl.duoc.rabbitmq_ms_consumidor;

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
    
    // Cola que recibirá todos los niveles de log
    public static final String ALL_LOGS_QUEUE = "all_logs_queue";
    
    // Cola que recibirá solamente los mensajes ERROR
    public static final String ERRORS_ONLY_QUEUE = "errors_only_queue";

    // Declara el DirectExchange
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    // Declara la cola para todos los logs
    @Bean
    public Queue allLogsQueue() {
        return new Queue(ALL_LOGS_QUEUE, true);
    }

    // Declara la cola exclusiva para errores
    @Bean
    public Queue errorsOnlyQueue() {
        return new Queue(ERRORS_ONLY_QUEUE, true);
    }

    // Binding: INFO -> all_logs_queue
    @Bean
    public Binding bindInfo() {
        return BindingBuilder
                .bind(allLogsQueue())
                .to(directExchange())
                .with("INFO");
    }

    // Binding: WARNING -> all_logs_queue
    @Bean
    public Binding bindWarning() {
        return BindingBuilder
                .bind(allLogsQueue())
                .to(directExchange())
                .with("WARNING");
    }

    // Binding: ERROR -> all_logs_queue
    @Bean
    public Binding bindErrorAllLogs() {
        return BindingBuilder
                .bind(allLogsQueue())
                .to(directExchange())
                .with("ERROR");
    }

    // Binding: ERROR -> errors_only_queue
    @Bean
    public Binding bindErrorOnly() {
        return BindingBuilder
                .bind(errorsOnlyQueue())
                .to(directExchange())
                .with("ERROR");
    }
}