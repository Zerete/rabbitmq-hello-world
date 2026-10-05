package cl.duoc.rabbitmq_ms_productor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    private final RabbitTemplate rabbitTemplate;

    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Publica el mensaje en el Exchange utilizando la severidad
    // como Routing Key.
    public void sendMessage(String level, String message) {
        rabbitTemplate.convertAndSend(
                "logs.direct",
                level,
                message
        );
        System.out.println(
                "[✓] Mensaje enviado - Routing Key: "
                        + level + " - " + message
        );
    }
}