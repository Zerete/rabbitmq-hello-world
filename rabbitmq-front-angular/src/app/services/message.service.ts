import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Estructura de la solicitud que Angular enviará al Productor.
export interface MessageRequest {
  level: string;
  message: string;
}

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  // Cliente HTTP utilizado para comunicarse con el Productor.
  private http = inject(HttpClient);
  
  // Endpoint REST del microservicio Productor.
  private readonly apiUrl = 'https://fictional-space-couscous-xrqx9pv69php74j-8081.app.github.dev/api/messages';

  // Envía el mensaje indicando la Routing Key.
  sendMessage(request: MessageRequest): Observable<string> {
    return this.http.post(
      this.apiUrl,
      request,
      { responseType: 'text' }
    );
  }
}