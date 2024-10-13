package tech.rodrigo.pedidos_ms.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tech.rodrigo.pedidos_ms.entity.Cliente;

public record ClienteDTO (
        @NotNull(message = "O nome não pode estar nulo.")
        @NotBlank(message = "O nome não pode estar vazio.")
        String nome,
        @NotNull(message = "O documento não pode estar nulo.")
        @NotBlank(message = "O documento não pode estar vazio.")
        String documento) {

    public static Cliente toEntity(ClienteDTO record) {
        return new Cliente(record.nome, record.documento);

    }


}

