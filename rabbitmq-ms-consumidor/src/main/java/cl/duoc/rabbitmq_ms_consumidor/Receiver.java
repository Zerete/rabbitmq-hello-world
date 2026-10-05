package cl.duoc.rabbitmq_ms_consumidor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @RabbitListener(queues = "hello")
    public void receiveMessage(String message) {
        System.out.println("[✓] Mensaje recibido: " + message);
    }
}