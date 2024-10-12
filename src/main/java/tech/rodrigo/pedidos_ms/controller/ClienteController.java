package tech.rodrigo.pedidos_ms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.rodrigo.pedidos_ms.controller.dto.ClienteDTO;
import tech.rodrigo.pedidos_ms.service.ClienteService;


@RestController
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> inserirCliente(@Validated @RequestBody ClienteDTO clienteDTO) {
        clienteService.save(ClienteDTO.toEntity(clienteDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);

    }
}
