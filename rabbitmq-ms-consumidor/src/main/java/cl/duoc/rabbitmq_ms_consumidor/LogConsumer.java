package cl.duoc.rabbitmq_ms_consumidor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LogConsumer {

    // Escucha la cola que recibe todos los logs
    @RabbitListener(queues = RabbitMQConfig.ALL_LOGS_QUEUE)
    public void receiveAllLogs(String message) {
        System.out.println("[MONITOR GENERAL] " + message);
    }

    // Escucha la cola que recibe solamente errores
    @RabbitListener(queues = RabbitMQConfig.ERRORS_ONLY_QUEUE)
    public void receiveErrorLogs(String message) {
        System.out.println("[ALERTA CRÍTICA] " + message);
    }
}

