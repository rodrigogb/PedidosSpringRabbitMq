package tech.rodrigo.pedidos_ms.controller.dto;

import java.util.List;
import java.util.Map;

public record ApiResponseDTO<T>(Map<String, Object> summary,
                                List<T> data,
                                PaginationResponseDTO pagination) {

}
