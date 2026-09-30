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

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody MessageRequest request) {
        sender.sendMessage(request.message());
        return ResponseEntity.ok("Mensaje enviado: " + request.message());
    }

    public record MessageRequest(String message) {
    }
}