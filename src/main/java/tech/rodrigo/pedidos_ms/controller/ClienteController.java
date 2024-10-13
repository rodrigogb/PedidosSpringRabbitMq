package tech.rodrigo.pedidos_ms.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.rodrigo.pedidos_ms.controller.dto.ClienteDTO;
import tech.rodrigo.pedidos_ms.handler.ErrorResponse;
import tech.rodrigo.pedidos_ms.handler.MessageHandler;
import tech.rodrigo.pedidos_ms.service.ClienteService;

@RestController
public class ClienteController {

    private final Logger logger = LoggerFactory.getLogger(ClienteController.class);
    private final MessageHandler messageHandler;
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService, MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
        this.clienteService = clienteService;
    }

    @PostMapping("/clientes")
    public ResponseEntity<Object> inserirCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            clienteService.save(ClienteDTO.toEntity(clienteDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
        } catch(Exception e) {
            logger.error(e.getMessage());
            ErrorResponse errorResponse = new ErrorResponse(messageHandler.getMessage("erro.interno.criar.cliente"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
