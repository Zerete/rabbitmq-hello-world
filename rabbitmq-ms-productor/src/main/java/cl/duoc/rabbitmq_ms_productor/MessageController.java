package cl.duoc.rabbitmq_ms_productor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final Sender sender;

    public MessageController(Sender sender) {
        this.sender = sender;
    }

    // Recibe la Routing Key y el mensaje desde la solicitud REST
    @PostMapping
    public ResponseEntity<String> sendMessage(
            @RequestBody MessageRequest request) {
        sender.sendMessage(
                request.level(),
                request.message()
        );
        return ResponseEntity.ok(
                "Mensaje enviado con Routing Key: "
                        + request.level()
        );
    }

    // Representa el cuerpo JSON recibido
    public record MessageRequest(
            String level,
            String message) {
    }
}