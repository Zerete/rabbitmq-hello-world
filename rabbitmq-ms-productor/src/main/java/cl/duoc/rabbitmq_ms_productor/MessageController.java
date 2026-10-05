package cl.duoc.rabbitmq_ms_productor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {
    private final Sender sender;

    public MessageController(Sender sender) {
        this.sender = sender;
    }

    @PostMapping
    public ResponseEntity<String> sendMessage(
            @RequestBody MessageRequest request) {
        // Envia usando level y message como en la guía 2
        sender.sendMessage(
                request.level(),
                request.message()
        );
        return ResponseEntity.ok(
                "Mensaje enviado con Routing Key: "
                        + request.level()
        );
    }

    public record MessageRequest(
            String level,
            String message) {
    }
}