package tech.rodrigo.pedidos_ms.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.rodrigo.pedidos_ms.controller.dto.ApiResponse;
import tech.rodrigo.pedidos_ms.controller.dto.PaginationResponse;
import tech.rodrigo.pedidos_ms.controller.dto.PedidoResponse;
import tech.rodrigo.pedidos_ms.service.PedidoService;

import java.util.Map;

@RestController
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/clientes/{clienteId}/pedidos")
    public ResponseEntity<ApiResponse<PedidoResponse>> listPedidos(@PathVariable("clienteId") Long clienteId,
                                                                   @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        var pageResponse = pedidoService.findAllByClienteId(clienteId, PageRequest.of(page, pageSize));
        var totalOnPedidos = pedidoService.findTotalOnPedidosByClienteId(clienteId);

        return ResponseEntity.ok(new ApiResponse<>(
                Map.of("totalOnPedidos", totalOnPedidos),
                pageResponse.getContent(),
                PaginationResponse.fromPage(pageResponse)
        ));

    }
}
