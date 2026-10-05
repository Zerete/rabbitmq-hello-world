import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MessageService } from './services/message.service';

@Component({
  selector: 'app-root',
  imports: [FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  private messageService = inject(MessageService);

  message = '';

  level = 'INFO';
  response = '';

  sendMessage(): void {
    // Evita enviar mensajes vacíos.
    if (!this.message.trim()) {
      return;
    }

    this.messageService.sendMessage({
      level: this.level,
      message: this.message
    }).subscribe({
      next: (result) => {
        this.response = result;
      },
      error: (error) => {
        console.error(error);
        this.response = 'Error al enviar el mensaje.';
      }
    });
  }
}